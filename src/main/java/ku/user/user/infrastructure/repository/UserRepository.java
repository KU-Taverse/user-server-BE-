package ku.user.user.infrastructure.repository;

import ku.user.user.infrastructure.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {
    Optional<UserEntity> findById(Long id);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByNickname(String nickname);

    UserEntity save(UserEntity userEntity);

    void delete(UserEntity userEntity);
}
