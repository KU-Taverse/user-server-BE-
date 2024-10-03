package ku.user.domain.inventory.dao;

import ku.user.domain.inventory.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
