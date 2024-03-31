package ku.user.user.infrastructure.entity;

import jakarta.persistence.Embeddable;
import ku.user.user.infrastructure.exception.InvalidPasswordException;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.time.Clock;
import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Password {
    private String password;

    private LocalDateTime expirationDate;

    private int failedCount;

    private long ttl;

    @Builder
    public Password(final String value, Clock clock) {
        this.ttl = 90 * 24 * 60 * 60;; // 90 days
        this.password = encodePassword(value);
        this.expirationDate = extendExpirationDate(clock);
    }

    public boolean isMatched(final String rawPassword) {
        if (failedCount >= 5) throw new InvalidPasswordException("5번 틀리셨습니다.");

        boolean matches = isMatches(rawPassword);
        updateFailedCount(matches);
        return matches;
    }

    public void changePassword(final String newPassword, final String oldPassword, Clock clock) {
        if (!isMatched(oldPassword)) {
            throw new InvalidPasswordException("비밀번호가 일치하지 않습니다.");
        }
        password = encodePassword(newPassword);
        expirationDate = extendExpirationDate(clock);
    }

    public boolean isExpiration() {
        return LocalDateTime.now().isAfter(expirationDate);
    }


    private String encodePassword(final String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    private LocalDateTime extendExpirationDate(Clock clock) {
        return LocalDateTime.now(clock).plusSeconds(ttl);
    }

    private void resetFailedCount() {
        this.failedCount = 0;
    }

    public void updateFailedCount(boolean matches) {
        if (matches)
            resetFailedCount();
        else
            increaseFailCount();
    }

    private void increaseFailCount() {
        this.failedCount++;
    }

    private boolean isMatches(String rawPassword) {
        return new BCryptPasswordEncoder().matches(rawPassword, this.password);
    }
}
