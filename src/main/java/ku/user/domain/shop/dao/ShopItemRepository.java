package ku.user.domain.shop.dao;

import ku.user.domain.shop.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopItemRepository extends JpaRepository<Item, Long> {
}
