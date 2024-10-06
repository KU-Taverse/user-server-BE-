package ku.user.domain.inventory.dao;

import ku.user.domain.inventory.domain.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<InventoryItem, Long> {
}
