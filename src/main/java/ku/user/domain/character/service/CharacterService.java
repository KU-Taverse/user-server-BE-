package ku.user.domain.character.service;

import ku.user.domain.character.dao.CharacterRepository;
import ku.user.domain.character.domain.Character;
import ku.user.domain.user.infrastructure.entity.UserEntity;
import ku.user.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final UserService userService;

    @Transactional
    public Character save(Character character) {
        return characterRepository.save(character);
    }

    @Transactional
    public Character saveByEmail(Character character, String email) {
        UserEntity userEntity = userService.getByEmail(email);
        character.setUserId(userEntity.getId());
        return save(character);
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

    /**
     * 이메일에 해당하는 유저 캐릭터 정보를 변경한다.
     *
     * @param email 이메일
     * @return 변경된 유저 엔티티
     */
    @Transactional
    public Character modifiedByEmail(String email, Character character) {
        UserEntity userEntity = userService.getByEmail(email);
        Character findCharacter = findByUserId(userEntity.getId());
        return findCharacter.update(character);
    }

    @Transactional
    public void delete(Long characterId) {
        characterRepository.delete(findById(characterId));
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

}
