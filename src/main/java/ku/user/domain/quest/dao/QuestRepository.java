package ku.user.domain.quest.dao;

import feign.Param;
import ku.user.domain.quest.domain.Quest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface QuestRepository extends JpaRepository<Quest,Long> {
    @Query("SELECT q.quest1 FROM Quest q WHERE q.userId = :userId AND q.createdAt = :createdAt")
    Optional<Quest> findQuestByUserIdAndCompletionDate(@Param("userId") Long userId, @Param("createdAt") LocalDateTime createdAt);

    /*@Query("SELECT q FROM Quest q WHERE q.userId = :userId AND DATE(q.createdAt) = CURRENT_DATE")
    Optional<Quest> findQuestByUserIdAndToday(@Param("userId") Long userId);*/

}
