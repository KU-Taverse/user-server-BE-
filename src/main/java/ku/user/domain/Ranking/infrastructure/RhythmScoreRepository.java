package ku.user.domain.Ranking.infrastructure;

import ku.user.domain.Ranking.domain.RhythmScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RhythmScoreRepository extends JpaRepository<RhythmScore,Long> {
    List<RhythmScore> findRhythmScoresByNickName(String nickname);

    Optional<RhythmScore> findTopByNickNameOrderByScoreDesc(String nickName);

    List<RhythmScore> findByNickNameAndCreatedAtBetween(String nickName, LocalDateTime startDate, LocalDateTime endDate);
}
