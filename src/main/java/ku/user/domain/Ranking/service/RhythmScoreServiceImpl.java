package ku.user.domain.Ranking.service;

import ku.user.domain.Ranking.domain.RhythmScore;
import ku.user.domain.Ranking.domain.Status;
import ku.user.domain.Ranking.infrastructure.RhythmScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RhythmScoreServiceImpl implements RhythmScoreService{
    private final RhythmScoreRepository repository;
    private final RankingService rankingService;

    @Transactional
    public RhythmScore saveScore(RhythmScore rhythmScore) {
        rankingService.updateScore(rhythmScore.getNickName(),rhythmScore.getCreatedAt(),"rhythms",rhythmScore.getScore());
        return repository.save(rhythmScore);
    }


    public List<RhythmScore> findScoresByNickName(String nickName) {
        List<RhythmScore> scores = repository.findRhythmScoresByNickName(nickName);
        return scores;
    }


    public List<RhythmScore> findScoresByNickNameAndDate(String nickName, LocalDateTime startDate, LocalDateTime endDate) {
        return repository.findByNickNameAndCreatedAtBetween(nickName, startDate, endDate);
    }

    @Transactional
    public void updateScoreStatus(String nickName) {
        List<RhythmScore> scores = repository.findRhythmScoresByNickName(nickName);

        if (scores.isEmpty()) {
            throw new IllegalArgumentException("해당 닉네임의 기록은 존재하지 않음: " + nickName);
        }

        boolean hasActiveRecord = false;
        for (RhythmScore score : scores) {
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


    public RhythmScore findHighestScoreByNickName(String nickName) {
        Optional<RhythmScore> optionalScore = repository.findTopByNickNameOrderByScoreDesc(nickName);
        return optionalScore.orElse(null);
    }
}
