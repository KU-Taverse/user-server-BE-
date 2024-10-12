package ku.user.domain.quest.dao;

import ku.user.domain.quest.domain.Quest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestRepository extends JpaRepository<Quest, String> {
}
