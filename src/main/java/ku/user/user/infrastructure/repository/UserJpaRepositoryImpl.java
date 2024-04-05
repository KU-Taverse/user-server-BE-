package ku.user.user.infrastructure.repository;

import ku.user.user.infrastructure.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserJpaRepositoryImpl implements UserRepository{
    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<UserEntity> findById(Long id) {
        return userJpaRepository.findById(id);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return userJpaRepository.findByEmail(email);
    }

    @Override
    public Optional<UserEntity> findByNickname(String nickname) {
        return userJpaRepository.findByNickname(nickname);
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        return userJpaRepository.save(userEntity);
    }

    @Override
    public boolean existEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }

    @Override
    public boolean existNickname(String nickname) {
        return userJpaRepository.existsByNickname(nickname);
    }

    @Override
    public void delete(UserEntity userEntity) {
        userJpaRepository.delete(userEntity);
    }
}
