package ku.user.domain.quest.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@Table(name = "users_quests")
public class UserQuest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long quest1;
    @Builder.Default
    private int quest1Progress = 0;
    private LocalDateTime quest1CompletedAt;


    private Long quest2;
    @Builder.Default
    private int quest2Progress = 0;
    private LocalDateTime quest2CompletedAt;

    private Long quest3;
    @Builder.Default
    private int quest3Progress = 0;
    private LocalDateTime quest3CompletedAt;

    private LocalDate createdAt;

    public static UserQuest from(Long userId, List<Long> randomQuestNumber) {
        return UserQuest.builder()
                .userId(userId)
                .quest1(randomQuestNumber.get(0))
                .quest2(randomQuestNumber.get(1))
                .quest3(randomQuestNumber.get(2))
                .createdAt(LocalDate.now())
                .build();
    }

    public void solve(Long questIndex) {
        if (quest1.equals(questIndex))
            quest1CompletedAt = LocalDateTime.now();
        if (quest2.equals(questIndex))
            quest2CompletedAt = LocalDateTime.now();
        if (quest3.equals(questIndex))
            quest3CompletedAt = LocalDateTime.now();
    }

    public void updateQuest1(int quest1Progress){
        this.quest1Progress = quest1Progress;
    }
    public void updateQuest2(int quest2Progress){
        this.quest2Progress = quest2Progress;
    }
    public void updateQuest3(int quest3Progress){
        this.quest3Progress = quest3Progress;
    }
}
