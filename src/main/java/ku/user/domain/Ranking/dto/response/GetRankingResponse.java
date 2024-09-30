package ku.user.domain.Ranking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetRankingResponse {
    private String characterName;
    private int score;
}
