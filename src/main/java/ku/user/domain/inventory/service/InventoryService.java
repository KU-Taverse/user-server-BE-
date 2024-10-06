package ku.user.domain.inventory.service;

import ku.user.domain.character.domain.Character;
import ku.user.domain.character.service.CharacterService;
import ku.user.domain.inventory.dao.InventoryRepository;
import ku.user.domain.inventory.dao.InventoryItemRepository;
import ku.user.domain.inventory.domain.Inventory;
import ku.user.domain.inventory.domain.InventoryItem;
import ku.user.domain.shop.domain.Item;
import ku.user.domain.shop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryItemRepository inventoryItemRepository;
    private final CharacterService characterService;
    private final ItemService itemService;


    @Transactional
    public Inventory findByCharacterId(Long characterId) {
        Optional<Inventory> inventoryOptional = inventoryRepository.findByCharacterId(characterId);
        if (inventoryOptional.isEmpty())
            throw new RuntimeException("해당하는 인벤토리가 없습니다.");
        return inventoryOptional.get();
    }

    @Transactional
    public Inventory save(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @Transactional
    public InventoryItem saveItem(InventoryItem item) {
        return inventoryItemRepository.save(item);
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
     * 유저 캐릭터에 대한 인벤토리를 찾는다.
     *
     * @param email 유저 이메일
     * @return 인벤토리
     */
    @Transactional(readOnly = true)
    public Inventory getInventoryByEmail(String email) {

        Character character = characterService.findByEmail(email);
        return findByCharacterId(character.getId());
    }

    @Transactional(readOnly = true)
    public List<InventoryItem> getInventoryItemList(Long inventoryId) {
        return inventoryItemRepository.findAllByInventoryId(inventoryId);
    }

    /**
     * 이메일해당하는 유저의 캐릭터가 아이템을 산다
     *
     * @param email
     * @param itemIndexId 아이템 indexId
     * @return
     */
    @Transactional
    public Inventory buyItem(String email, Long itemId) {
        //아이템 id에 해당하는 아이템을 가져온다
        Item findItem = itemService.findById(itemId);
        //TODO inventoryItemRepository.
        //캐릭터가 돈을 지불한다.
        Character character = characterService.payPriceByEmail(email, findItem.getPrice());
        //캐릭터가 소유한 인벤토리를 조회한다.
        Inventory findInventory = findByCharacterId(character.getId());
        //인벤토리에 아이템을 추가한다.
        addItem(findInventory, findItem);
        return findInventory;
    }

    /**
     * 인벤토리에 아이템을 저장하고 추가한다.
     *
     * @param findInventory 인벤토리
     * @param item          추가할 아이템
     * @return 인벤토리
     * 트랙잭션 전파를 사용한다.
     */
    private void addItem(Inventory findInventory, Item item) {
        InventoryItem inventoryItem = InventoryItem.from(findInventory, item);
        saveItem(inventoryItem);
    }

}
