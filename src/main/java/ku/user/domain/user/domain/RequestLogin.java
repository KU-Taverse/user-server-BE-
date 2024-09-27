package ku.user.domain.user.domain;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class RequestLogin {
    @NotNull
    private String email;

    @NotNull
    private String password;
}
