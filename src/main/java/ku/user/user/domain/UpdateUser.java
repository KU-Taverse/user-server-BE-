package ku.user.user.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateUser {
    private String email;
    private String nickname;

    @Builder
    public UpdateUser(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }
}
