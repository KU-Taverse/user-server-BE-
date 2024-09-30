package ku.user.domain.quest.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.relational.core.sql.In;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Table(name = "quests")
public class Quest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Integer quest1;
    private LocalDateTime quest1CompletedAt;

    private Integer quest2;
    private LocalDateTime quest2CompletedAt;

    private Integer quest3;
    private LocalDateTime quest3CompletedAt;

    private LocalDate createdAt;

    public static Quest from(Long userId, List<Integer> randomQuestNumber) {
        return Quest.builder()
                .userId(userId)
                .quest1(randomQuestNumber.get(0))
                .quest2(randomQuestNumber.get(1))
                .quest3(randomQuestNumber.get(2))
                .createdAt(LocalDate.now())
                .build();
    }

    public void solve(Integer questIndex) {
        if (quest1.equals(questIndex))
            quest1CompletedAt = LocalDateTime.now();
        if (quest2.equals(questIndex))
            quest2CompletedAt = LocalDateTime.now();
        if (quest3.equals(questIndex))
            quest3CompletedAt = LocalDateTime.now();
    }
}
