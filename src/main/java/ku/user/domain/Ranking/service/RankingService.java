package ku.user.domain.Ranking.service;

import java.time.LocalDateTime;
import java.util.Set;

public interface RankingService {
    public void updateScore(String characterName, LocalDateTime createdAt, String gameKey, int newScore);

    public Set<Object> getTopRankers(String gameKey, int n);

    public Long getUserRank(String gameKey, String characterName);
}
