package ku.user.user.infrastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserJpaRepositoryImplTest {
    @MockBean
    private UserJpaRepository userJpaRepository;

    @Autowired
    public UserJpaRepositoryImplTest(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Test
    public void findByIdTest() {
        // given
        Long userId = 1L;
        UserEntity user = new UserEntity();
        user.setId(userId);

        // when
        when(userJpaRepository.findById(userId)).thenReturn(Optional.of(user));

        // then
        Optional<UserEntity> foundUser = userJpaRepository.findById(userId);
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getId()).isEqualTo(userId);
    }

    @Test
    public void saveTest() {
        // given
        UserEntity userToSave = new UserEntity();
        userToSave.setEmail("test@example.com");
        userToSave.setNickname("testuser");

        // when
        when(userJpaRepository.save(userToSave)).thenReturn(userToSave);

        // then
        UserEntity savedUser = userJpaRepository.save(userToSave);
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getEmail()).isEqualTo(userToSave.getEmail());
        assertThat(savedUser.getNickname()).isEqualTo(userToSave.getNickname());
    }

    @Test
    public void deleteTest() {
        // given
        UserEntity userToDelete = new UserEntity();
        userToDelete.setId(1L);

        // when
        doNothing().when(userJpaRepository).delete(userToDelete);

        // then
        userJpaRepository.delete(userToDelete);
        verify(userJpaRepository).delete(userToDelete);
    }

    @Test
    public void findByEmailTest() {
        // given
        String email = "test@example.com";
        UserEntity user = new UserEntity();
        user.setEmail(email);

        // when
        when(userJpaRepository.findByEmail(email)).thenReturn(Optional.of(user));

        // then
        Optional<UserEntity> foundUserOptional = userJpaRepository.findByEmail(email);
        assertThat(foundUserOptional).isPresent();
        UserEntity foundUser = foundUserOptional.get();
        assertThat(foundUser.getEmail()).isEqualTo(email);
        assertThat(foundUserOptional).isEqualTo(Optional.of(user));
    }

    @Test
    public void findByNickname() {
        // given
        String nickname = "kamothi";
        UserEntity user = new UserEntity();
        user.setNickname(nickname);

        // when
        when(userJpaRepository.findByNickname(nickname)).thenReturn(Optional.of(user));

        // then
        Optional<UserEntity> foundUser = userJpaRepository.findByNickname(nickname);
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getNickname()).isEqualTo(nickname);
    }

}