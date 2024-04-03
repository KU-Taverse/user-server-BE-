package ku.user.user.service;

import ku.user.user.domain.CreateUser;
import ku.user.user.domain.UpdateUser;
import ku.user.user.infrastructure.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserEntity create(CreateUser createUserDto);
    void login();
    UserEntity getById(Long id);
    UserEntity getByEmail(String email);
    UserEntity update(Long id, UpdateUser updateUserDto);
    void delete(Long id/*, 여기에 인증으로 삭제하게 할까?(등록했던 이메일로) */);
}
