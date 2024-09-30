package ku.user.domain.ranking.dto.response;

import ku.user.domain.ranking.domain.RhythmScore;

public record SaveRhythmResponse(Long id, String nickName, int score) {
    public static SaveRhythmResponse fromEntity(RhythmScore rhythmScore) {
        return new SaveRhythmResponse(
                rhythmScore.getId(),
                rhythmScore.getNickName(),
                rhythmScore.getScore()
        );
    }
}