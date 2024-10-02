package ku.user.domain.character.service;

import ku.user.domain.character.dao.CharacterRepository;
import ku.user.domain.character.domain.Character;
import ku.user.domain.character.exception.CharacterCreateException;
import ku.user.domain.character.exception.CurrentMoneyLeakException;
import ku.user.domain.inventory.service.InventoryService;
import ku.user.domain.user.infrastructure.entity.UserEntity;
import ku.user.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final UserService userService;
    private final InventoryService inventoryService;

    @Transactional
    public Character save(Character character) {
        try {
            return characterRepository.save(character);
        } catch (DataAccessException e) {
            throw new CharacterCreateException();
        }
    }

    /**
     * 이에밀에 해당하는 유저의 캐릭터를 생성한다.
     * 캐릭터에 해당하는 inventory를 조기화한다.
     * @param character
     * @param email
     * @return
     */
    @Transactional
    public Character saveByEmail(Character character, String email) {
        UserEntity userEntity = userService.getByEmail(email);
        character.setUserId(userEntity.getId());
        Character saveCharacter = save(character);
        inventoryService.createInventoryByCharacterId(character.getId());

        return character;
    }

    @Transactional(readOnly = true)
    public Character findById(Long characterId) {
        Optional<Character> character = characterRepository.findById(characterId);
        if (character.isEmpty())
            throw new RuntimeException("해당하는 유저가 없습니다");
        return character.get();
    }

    @Transactional(readOnly = true)
    public Character findByUserId(Long userId) {
        Optional<Character> character = characterRepository.findByUserId(userId);
        if (character.isEmpty())
            throw new RuntimeException("해당하는 유저가 없습니다");
        return character.get();
    }

    @Transactional
    public Character findByEmail(String email) {
        UserEntity userEntity = userService.getByEmail(email);
        return findByUserId(userEntity.getId());
    }

    /**
     * 이메일에 해당하는 유저 캐릭터 정보를 변경한다.
     *
     * @param email 이메일
     * @return 변경된 유저 엔티티
     */
    @Transactional
    public Character modifiedByEmail(String email, Character character) {
        Character findCharacter = findByEmail(email);
        return findCharacter.update(character);
    }

    @Transactional
    public void delete(Long characterId) {
        characterRepository.delete(findById(characterId));
    }

    @Transactional
    public void deleteByEmail(String email){
        Character character = findByEmail(email);
        delete(character.getId());
    }

    @Transactional(readOnly = true)
    public Boolean checkCharacterExistByEmail(String email) {
        UserEntity userEntity = userService.getByEmail(email);
        try {
            findByUserId(userEntity.getId());
            return true;
        } catch (RuntimeException exception) {
            return false;
        }
    }

    @Transactional
    public Character payPriceByEmail(String email, int price) {
        Character character = findByEmail(email);
        if (character.getCurrentMoney() < price) {
            throw new CurrentMoneyLeakException();
        }
        character.pay(price);
        return character;
    }
}
