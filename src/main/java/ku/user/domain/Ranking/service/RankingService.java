package ku.user.domain.Ranking.service;

import ku.user.domain.Ranking.dto.response.GetRankingResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface RankingService {
    public void updateScore(String characterName, LocalDateTime createdAt, String gameKey, int newScore);

    public List<GetRankingResponse> getTopRankers(String gameKey, int n);

    public Long getUserRank(String gameKey, String characterName);
}
