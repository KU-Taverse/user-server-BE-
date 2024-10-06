package ku.user.domain.inventory.dao;

import ku.user.domain.inventory.domain.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {

    List<InventoryItem> findAllByInventoryId(Long inventoryId);

    //TODO boolean existsByInventoryIdAndU
}
