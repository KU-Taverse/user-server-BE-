package ku.user.user.service;

import ku.user.user.domain.CreateUser;
import ku.user.user.infrastructure.entity.Password;
import ku.user.user.infrastructure.entity.UserEntity;
import ku.user.user.infrastructure.entity.UserStatus;
import ku.user.user.infrastructure.repository.UserRepository;
import ku.user.user.mock.FakeUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Repository;

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
                .email("rhkddlf7911@naver.com")
                .password("1234")
                .nickname("kamothi")
                .build();
        // when
        UserEntity result = userService.create(userCreate);

        // then
        assertThat(result.getStatus()).isEqualTo(UserStatus.GENERAL);
        assertThat(result.getEmail()).isEqualTo("rhkddlf7911@naver.com");
        assertThat(result.getNickname()).isEqualTo("kamothi");
        assertThat(result.getPassword().isMatched("1234")).isTrue();

    }

}