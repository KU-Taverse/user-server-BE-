package ku.user.user.service;

import jakarta.transaction.Transactional;
import ku.user.user.domain.CreateUser;
import ku.user.user.domain.UpdateUser;
import ku.user.user.infrastructure.entity.UserEntity;
import ku.user.user.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    @Override
    @Transactional
    public UserEntity create(CreateUser createUserDto) {
        UserEntity userEntity = createUserDto.toEntity();
        // 여기에 나중에 인증 과정을 넣을 수도 있음.
        userRepository.save(userEntity);
        return userEntity;
    }

    @Override
    public void login() {

    }

    @Override
    public UserEntity getById(String id) {
        return null;
    }

    @Override
    public UserEntity getByEmail(String id) {
        return null;
    }

    @Override
    public UserEntity update(String id, UpdateUser updateUserDto) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
