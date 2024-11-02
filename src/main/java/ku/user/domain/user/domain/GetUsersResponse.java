package ku.user.domain.user.domain;

import ku.user.domain.user.infrastructure.entity.UserAccountStatus;
import ku.user.domain.user.infrastructure.entity.UserEntity;
import ku.user.domain.user.infrastructure.entity.UserStatus;

public record GetUsersResponse(Long userId, String email, UserStatus role, UserAccountStatus accountStatus) {


    public static GetUsersResponse fromEntity(UserEntity userEntity){
        return new GetUsersResponse(
                userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getStatus(),
                userEntity.getAccountStatus()
        );
    }
}
