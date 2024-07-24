package ku.user.user.service;

import ku.user.user.domain.CreateUser;
import ku.user.user.domain.UpdateUser;
import ku.user.user.infrastructure.entity.UserEntity;
import ku.user.user.infrastructure.entity.UserStatus;
import ku.user.user.infrastructure.exception.InvalidPasswordException;
import ku.user.user.infrastructure.repository.UserRepository;
import ku.user.user.mock.FakeUserRepository;
import ku.user.user.service.exception.ResourceNotFoundException;
import ku.user.user.service.exception.UserExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Clock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    private UserServiceImpl userService;

    @BeforeEach
    void init() {
        UserRepository fakeUserRepository = new FakeUserRepository();
        this.userService = new UserServiceImpl(fakeUserRepository);
        fakeUserRepository.save(UserEntity.builder()
                .id(1L)
                .email("rhkddlf7911@naver.com")
                .nickname("kamothi")
                .password("1234")
                .build());
    }

    @Test
    void create를_하면_유저가_생성된다(){
        // given
        CreateUser userCreate = CreateUser.builder()
                .email("rhkddlf7911@aver.com")
                .password("123")
                .nickname("kamoth")
                .build();
        // when
        UserEntity result = userService.create(userCreate);

        // then
        assertThat(result.getStatus()).isEqualTo(UserStatus.GENERAL);
        assertThat(result.getEmail()).isEqualTo("rhkddlf7911@aver.com");
        assertThat(result.getNickname()).isEqualTo("kamoth");
        assertThat(result.getPassword().isMatched("123")).isTrue();
    }

    @Test
    void 이미_유저가_존재하는_이메일이면_예외를_던진다(){
        // given
        CreateUser userCreate = CreateUser.builder()
                .email("rhkddlf7911@naver.com")
                .password("1234")
                .nickname("kamothi")
                .build();
        // when
        assertThrows(UserExistsException.class,()->{
            UserEntity result = userService.create(userCreate);
        });
        // then
    }

    @Test
    void 이미_유저가_존재하는_닉네임이면_예외를_던진다(){
        // given
        CreateUser userCreate = CreateUser.builder()
                .email("rhkddlf791@naver.com")
                .password("1234")
                .nickname("kamothi")
                .build();
        // when
        assertThrows(UserExistsException.class,()->{
            UserEntity result = userService.create(userCreate);
        });
        // then
    }

    @Test
    void getById로_user를_가져올_수_있다() {
        // given

        // when
        UserEntity result = userService.getById(1L);

        // then
        assertThat(result.getNickname()).isEqualTo("kamothi");
    }

    @Test
    void getById로_user를_가져올_때_없으면_예외를_던진다() {
        // given

        // when
        assertThrows(ResourceNotFoundException.class, () -> {
            userService.getById(2L);
        });
        // then
    }

    @Test
    void getByEmail로_user를_가져올_수_있다() {
        // given
        String email = "rhkddlf7911@naver.com";

        // when
        UserEntity result = userService.getByEmail(email);

        // then
        assertThat(result.getNickname()).isEqualTo("kamothi");
    }

    @Test
    void getByEmail로_user를_가져올_때_없으면_예외를_던진다() {
        // given

        // when
        assertThrows(ResourceNotFoundException.class, () -> {
            userService.getByEmail("kamothi@naver.com");
        });
        // then
    }

    @Test
    void update_를_하면_entity_값이_수정된다(){
        // given
        UpdateUser updateUser = UpdateUser.builder()
                .email("rhkddlf7911@gmail.com")
                .nickname("ka")
                .build();

        // when
        UserEntity userEntity = userService.update(1L,updateUser);

        // then
        assertThat(userEntity.getEmail()).isEqualTo("rhkddlf7911@gmail.com");
        assertThat(userEntity.getNickname()).isEqualTo("ka");
    }

}