package ku.user.domain.ranking.service;

import ku.user.domain.ranking.dto.response.GetRankingResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface RankingService {
    public void updateScore(String characterName, LocalDateTime createdAt, String gameKey, int newScore);

    public List<GetRankingResponse> getTopRankers(String gameKey, int n);

    public Long getUserRank(String gameKey, String characterName);
}
