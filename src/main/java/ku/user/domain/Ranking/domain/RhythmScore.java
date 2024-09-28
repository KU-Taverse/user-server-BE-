package ku.user.domain.Ranking.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
// 점수, 캐릭터명, 날짜, 재화
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Table(name = "rhythm_scores")
public class RhythmScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickName;

    private int score;

    private LocalDateTime createdAt;
}
