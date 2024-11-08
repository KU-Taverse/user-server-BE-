package ku.user.domain.character.dao;

import ku.user.domain.character.domain.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {

    Optional<Character> findByUserId(Long userId);

    boolean existsCharacterByNickname(String nickname);
}
