package ku.user.domain.Ranking.service;

import ku.user.domain.Ranking.domain.Status;
import ku.user.domain.Ranking.domain.SteppingStonesScore;
import ku.user.domain.Ranking.infrastructure.SteppingScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SteppingStonesScoreServiceImpl implements SteppingStonesScoreService{
    private final SteppingScoreRepository repository;
    @Transactional
    public SteppingStonesScore saveScore(SteppingStonesScore steppingStonesScore) {
        return repository.save(steppingStonesScore);
    }


    public List<SteppingStonesScore> findScoresByNickName(String nickName) {
        List<SteppingStonesScore> scores = repository.findSteppingStonesScoresByNickName(nickName);
        return scores;
    }


    public List<SteppingStonesScore> findScoresByNickNameAndDate(String nickName, LocalDateTime startDate, LocalDateTime endDate) {
        return repository.findByNickNameAndCreatedAtBetween(nickName, startDate, endDate);
    }

    @Transactional
    public void updateScoreStatus(String nickName) {
        List<SteppingStonesScore> scores = repository.findSteppingStonesScoresByNickName(nickName);

        if (scores.isEmpty()) {
            throw new IllegalArgumentException("해당 닉네임의 기록은 존재하지 않음: " + nickName);
        }

        boolean hasActiveRecord = false;
        for (SteppingStonesScore score : scores) {
            if (score.getStatus() == Status.ACTIVE) {
                score.setStatus(Status.INACTIVE);
                hasActiveRecord = true;
            }
        }

        if (!hasActiveRecord) {
            System.out.println("이미 비활성화 되어 있는 캐릭터입니다.");
            return;
        }

        repository.saveAll(scores);
    }


    public SteppingStonesScore findHighestScoreByNickName(String nickName) {
        Optional<SteppingStonesScore> optionalScore = repository.findTopByNickNameOrderByScoreDesc(nickName);
        return optionalScore.orElse(null);
    }
}
