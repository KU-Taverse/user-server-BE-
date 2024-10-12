package ku.user.domain.quest.dao;

import feign.Param;
import ku.user.domain.quest.domain.UserQuest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface UserQuestRepository extends JpaRepository<UserQuest,Long> {
    @Query("SELECT q FROM UserQuest q WHERE q.userId = :userId AND q.createdAt = :createdAt")
    Optional<UserQuest> findQuestByUserIdAndCompletionDate(@Param("userId") Long userId, @Param("createdAt") LocalDate createdAt);

    Optional<UserQuest> findByUserId(Long userId);
    /*@Query("SELECT q FROM Quest q WHERE q.userId = :userId AND DATE(q.createdAt) = CURRENT_DATE")
    Optional<Quest> findQuestByUserIdAndToday(@Param("userId") Long userId);*/

}
