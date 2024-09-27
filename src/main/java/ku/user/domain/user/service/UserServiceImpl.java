package ku.user.domain.user.service;

import jakarta.transaction.Transactional;
import ku.user.domain.user.domain.CreateUser;
import ku.user.domain.user.domain.UpdateUser;
import ku.user.domain.user.domain.UserDto;
import ku.user.domain.user.infrastructure.entity.UserEntity;
import ku.user.domain.user.infrastructure.repository.UserRepository;
import ku.user.domain.user.service.exception.ResourceNotFoundException;
import ku.user.domain.user.service.exception.UserExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username).get();

        if(user == null){
            throw new UsernameNotFoundException(username);
        }
        // 마지막 어레이는 로그인 되었을 때 그 다음에 할 수 있는 작업에서의 권한 추가
        return new User(user.getEmail(),user.getPassword().getPassword()
                ,true,true,true,true,new ArrayList<>());
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
    @Transactional
    public UserEntity update(Long id, UpdateUser updateUserDto) {
        UserEntity user = getById(id);
        // 아직 만드는 중
        user.setEmail(updateUserDto.getEmail());
        user.setNickname(updateUserDto.getNickname());
        userRepository.save(user);
        return user;
    }

    @Override
    public void delete(Long id) {
        // 인증해야지만 삭제하게 할 것인가?
    }

    @Override
    public UserDto getUserDetailsByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email).get();

        if(user == null)
            throw new UsernameNotFoundException(email);

        return UserDto.from(user);
    }
}