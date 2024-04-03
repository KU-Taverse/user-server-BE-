package ku.user.user.infrastructure;

import ku.user.user.infrastructure.entity.Password;
import ku.user.user.infrastructure.exception.InvalidPasswordException;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PasswordTest {

    @Test
    public void 정상적으로_패스워드가_생성이_된다(){
        // given
        long ttl =  90 * 24 * 60 * 60;
        String password = "dddd";
        Instant now = Instant.now();
        Clock fixedClock = Clock.fixed(now, ZoneId.systemDefault());
        LocalDateTime expirationDate = LocalDateTime.now(fixedClock).plusSeconds(ttl);

        // when
        Password password1 = new Password(password,fixedClock);

        // then
        boolean matches = new BCryptPasswordEncoder().matches(password, password1.getPassword());
        assertThat(matches).isTrue();
        assertThat(password1.getTtl()).isEqualTo(ttl);
        assertThat(password1.getExpirationDate()).isEqualTo(expirationDate);
    }

    @Test
    public void 비밀번호를_5회_이상_틀릴_경우_예외를_내뱉는다(){
        // given
        String password = "dddd";
        Password pass = new Password(password, Clock.systemDefaultZone());

        // when
        for(int i=0;i<5;i++) pass.updateFailedCount(false);

        // then
        assertThrows(InvalidPasswordException.class, () -> {
            pass.isMatched("password");
        });

    }

    @Test
    public void 비밀번호가_일치할_경우_true_를_반환한다(){
        // given
        String password = "dddd";
        Password pass = new Password(password, Clock.systemDefaultZone());

        // when
        boolean b =  pass.isMatched("dddd");

        // then
       assertThat(b).isTrue();
    }

    @Test
    public void 비밀번호를_5회_이상_틀리고_맞을_경우_예외를_내뱉는다(){
        // given
        String password = "dddd";
        Password pass = new Password(password, Clock.systemDefaultZone());

        // when
        for(int i=0;i<5;i++) pass.updateFailedCount(false);

        // then
        assertThrows(InvalidPasswordException.class, () -> {
            pass.isMatched("dddd");
        });
    }

    @Test
    public void 비밀번호를_변경하면_만료일이_연장된다(){
        // given
        String password = "dddd";
        Password pass = new Password(password, Clock.systemDefaultZone());

        // when
        LocalDateTime originalExpirationDate = pass.getExpirationDate();
        pass.changePassword("newpassword", password, Clock.systemDefaultZone());

        // then
        assertThat(pass.getExpirationDate()).isAfter(originalExpirationDate);
    }

    @Test
    public void 비밀번호를_바꿀_때_기존_비밀번호가_맞아야_한다(){
        // given
        String password = "dddd";
        Password pass = new Password(password, Clock.systemDefaultZone());

        // when & then
        assertThrows(InvalidPasswordException.class, () -> {
            pass.changePassword("newpassword", "wrongpassword", Clock.systemDefaultZone());
        });
    }
}