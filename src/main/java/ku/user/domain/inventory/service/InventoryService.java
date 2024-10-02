package ku.user.domain.inventory.service;

import ku.user.domain.character.domain.Character;
import ku.user.domain.character.service.CharacterService;
import ku.user.domain.inventory.dao.InventoryRepository;
import ku.user.domain.inventory.dao.ItemRepository;
import ku.user.domain.inventory.domain.Inventory;
import ku.user.domain.inventory.domain.Item;
import ku.user.domain.inventory.domain.ItemType;
import ku.user.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ItemRepository itemRepository;
    private final UserService userService;
    private final CharacterService characterService;


    @Transactional
    public Inventory findByUserId(Long userId){
        Optional<Inventory> inventoryOptional = inventoryRepository.findByUserId(userId);
        if(inventoryOptional.isEmpty())
            throw new RuntimeException("해당하는 인벤토리가 없습니다. 회원가입된 아이디가 아닙니다.");
        return inventoryOptional.get();
    }

    @Transactional
    public Inventory save(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Transactional
    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    /**
     * 유저가 생성될때, 인벤토리 엔티티를 추가한다.
     *
     * @param characterId 유저 캐릭터 id
     * @return 생성된 인벤토리
     */
    @Transactional
    public Inventory createInventoryByCharacterId(Long characterId) {
        Inventory inventory = Inventory.from(characterId);
        return save(inventory);
    }

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

    /**
     * 이메일해당하는 유저가 아이템을 산다
     * @param email
     * @param itemType
     * @return
     */
    @Transactional
    public Inventory buyItem(String email, ItemType itemType) {
        Character character = characterService.payPriceByEmail(email, itemType.getPrice());
        Long userId = character.getUserId();
        Inventory findInventory = findByUserId(userId);
        Inventory updatInventory = addItem(findInventory, itemType);
        return updatInventory;
    }

    /**
     * 인벤토리에 아이템을 저장하고 추가한다.
     * @param findInventory 인벤토리
     * @param itemType 추가할 아이템
     * @return 인벤토리
     * 트랙잭션 전파를 사용한다.
     */
    private Inventory addItem(Inventory findInventory, ItemType itemType) {
        Item item = Item.from(findInventory, itemType);
        Item saveItem = saveItem(item);
        findInventory.addItem(saveItem);
        return findInventory;
    }

}
