package ku.user.domain.Ranking.infrastructure;

import ku.user.domain.Ranking.domain.RhythmScore;
import ku.user.domain.Ranking.domain.SteppingStonesScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SteppingScoreRepository extends JpaRepository<SteppingStonesScore,Long> {
    List<SteppingStonesScore> findSteppingStonesScoresByNickName(String nickname);

    Optional<SteppingStonesScore> findTopByNickNameOrderByScoreDesc(String nickName);

    List<SteppingStonesScore> findByNickNameAndCreatedAtBetween(String nickName, LocalDateTime startDate, LocalDateTime endDate);
}
