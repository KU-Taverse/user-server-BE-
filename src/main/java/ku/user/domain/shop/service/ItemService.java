package ku.user.domain.shop.service;

import ku.user.domain.shop.dao.ItemRepository;
import ku.user.domain.shop.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional(readOnly = true)
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Item findById(Long itemId){
        Optional<Item> itemOptional = itemRepository.findById(itemId);
        if(itemOptional.isEmpty())
            throw new RuntimeException("해당하는 아이템이 없습니다");
        return itemOptional.get();
    }
    @Transactional
    public Item addShopItem(Item shopItem) {
        return itemRepository.save(shopItem);
    }
}
