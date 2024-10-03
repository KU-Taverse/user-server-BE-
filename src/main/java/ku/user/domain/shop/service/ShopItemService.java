package ku.user.domain.shop.service;

import ku.user.domain.shop.dao.ShopItemRepository;
import ku.user.domain.shop.domain.ShopItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopItemService {

    private final ShopItemRepository shopItemRepository;
    public List<ShopItem> findAll() {
        return shopItemRepository.findAll();
    }
}
