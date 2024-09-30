package ku.user.domain.ranking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class GetRankingResponse {
    private String characterName;
    private int score;
    private LocalDateTime createdAt;
}
