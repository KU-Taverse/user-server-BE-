package ku.user.domain.user.infrastructure.repository;

import ku.user.domain.user.infrastructure.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<UserEntity> findById(Long id);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByNickname(String nickname);

    UserEntity save(UserEntity userEntity);

    void delete(UserEntity userEntity);

    boolean existEmail(String email);

    boolean existNickname(String nickname);

    List<UserEntity> findAll();
}
