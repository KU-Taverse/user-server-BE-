package ku.user.domain.ranking.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import ku.user.domain.ranking.domain.Status;
import ku.user.domain.ranking.domain.SteppingStonesScore;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SaveSteppingRequest {
    @NotBlank(message = "닉네임은 필수 항목")
    private String nickName;

    @Min(value = 0, message = "점수는 0 이상이어야 합니다.")
    private int score;

    @Min(value = 0, message = "코인은 0 이상이어야 합니다.")
    private int coin;

    public static SteppingStonesScore toEntity(SaveSteppingRequest saveSteppingRequest){
        return SteppingStonesScore.builder()
                .nickName(saveSteppingRequest.getNickName())
                .score(saveSteppingRequest.getScore())
                .coin(saveSteppingRequest.getCoin())
                .createdAt(LocalDateTime.now())
                .status(Status.ACTIVE)
                .build();

    }
}
