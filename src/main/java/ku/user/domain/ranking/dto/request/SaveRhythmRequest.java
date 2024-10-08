package ku.user.domain.ranking.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import ku.user.domain.ranking.domain.RhythmScore;
import ku.user.domain.ranking.domain.Status;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SaveRhythmRequest {
    @NotBlank(message = "닉네임은 필수 항목")
    private String nickName;

    @Min(value = 0, message = "점수는 0 이상이어야 합니다.")
    private int score;


    public static RhythmScore toEntity(SaveRhythmRequest saveRhythmRequest){
        return RhythmScore.builder()
                .nickName(saveRhythmRequest.getNickName())
                .score(saveRhythmRequest.getScore())
                .status(Status.ACTIVE)
                .createdAt(LocalDateTime.now())
                .build();

    }
}
