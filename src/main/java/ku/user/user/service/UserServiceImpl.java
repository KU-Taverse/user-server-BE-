package ku.user.user.service;

import jakarta.transaction.Transactional;
import ku.user.user.domain.CreateUser;
import ku.user.user.domain.UpdateUser;
import ku.user.user.infrastructure.entity.UserEntity;
import ku.user.user.infrastructure.repository.UserRepository;
import ku.user.user.service.exception.ResourceNotFoundException;
import ku.user.user.service.exception.UserExistsException;
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

        // 이미 존재하는 이메일과 이름이라면 예외를 던져야함
        if(userRepository.existEmail(userEntity.getEmail())) throw new UserExistsException("이미 존재하는 이메일");
        if(userRepository.existNickname(userEntity.getNickname())) throw new UserExistsException("이미 존재하는 닉네임");

        // 여기에 나중에 인증 과정을 넣을 수도 있음.
        userRepository.save(userEntity);
        return userEntity;
    }

    @Override
    public void login() {

    }

    @Override
    public UserEntity getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));
    }

    @Override
    public UserEntity getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", email));
    }

    @Override
    public UserEntity update(Long id, UpdateUser updateUserDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
