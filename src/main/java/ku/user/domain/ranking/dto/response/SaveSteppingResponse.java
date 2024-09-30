package ku.user.domain.ranking.dto.response;

import ku.user.domain.ranking.domain.SteppingStonesScore;

public record SaveSteppingResponse(Long id, String nickName, int score, int coin) {
    public static SaveSteppingResponse fromEntity(SteppingStonesScore steppingStonesScore) {
        return new SaveSteppingResponse(
                steppingStonesScore.getId(),
                steppingStonesScore.getNickName(),
                steppingStonesScore.getScore(),
                steppingStonesScore.getCoin()
        );
    }
}
