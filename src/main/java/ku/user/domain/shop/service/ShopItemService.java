package ku.user.domain.shop.service;

import ku.user.domain.shop.dao.ShopItemRepository;
import ku.user.domain.shop.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopItemService {

    private final ShopItemRepository shopItemRepository;

    @Transactional(readOnly = true)
    public List<Item> findAll() {
        return shopItemRepository.findAll();
    }

    @Transactional
    public Item addShopItem(Item shopItem) {
        return shopItemRepository.save(shopItem);
    }
}
