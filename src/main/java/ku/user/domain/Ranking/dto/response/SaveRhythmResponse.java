package ku.user.domain.Ranking.dto.response;

import ku.user.domain.Ranking.domain.RhythmScore;
import ku.user.domain.Ranking.domain.SteppingStonesScore;

public record SaveRhythmResponse(Long id, String nickName, int score) {
    public static SaveRhythmResponse fromEntity(RhythmScore rhythmScore) {
        return new SaveRhythmResponse(
                rhythmScore.getId(),
                rhythmScore.getNickName(),
                rhythmScore.getScore()
        );
    }
}