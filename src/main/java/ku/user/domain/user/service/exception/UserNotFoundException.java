package ku.user.domain.user.service.exception;

import ku.user.global.exception.CustomException;
import ku.user.global.exception.ErrorCode;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException(String message) {
        super(message, ErrorCode.USER_NOT_FOUND);
    }
}
