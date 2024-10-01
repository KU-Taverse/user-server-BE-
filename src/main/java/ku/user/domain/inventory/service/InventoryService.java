package ku.user.domain.inventory.service;

import ku.user.domain.inventory.dao.InventoryRepository;
import ku.user.domain.inventory.dao.ItemRepository;
import ku.user.domain.inventory.domain.Inventory;
import ku.user.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ItemRepository itemRepository;
    private final UserService userService;

    /**
     * 유저 이메일에 대한 인벤토리를 찾는다.
     *
     * @param email 유저 이메일
     * @return 인벤토리
     */
    @Transactional(readOnly = true)
    public Inventory getInventoryByEmail(String email) {

        Long userId = userService.getByEmail(email).getId();
        Optional<Inventory> inventoryOptional = inventoryRepository.findByUserId(userId);

        return inventoryOptional.get();
    }


    @Transactional
    public Inventory save(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    /**
     * 유저가 생성될때, 인벤토리 엔티티를 추가한다.
     * @param userId 유저id
     * @return 생성된 인벤토리
     */
    @Transactional
    public Inventory createInventory(Long userId) {
        Inventory inventory = Inventory.from(userId);
        return save(inventory);
    }
}
