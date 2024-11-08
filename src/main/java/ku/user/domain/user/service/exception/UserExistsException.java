package ku.user.domain.user.service.exception;

import ku.user.global.exception.CustomException;
import ku.user.global.exception.ErrorCode;

public class UserExistsException extends CustomException {
    public UserExistsException(String msg) {
        super(msg, ErrorCode.INVALID_USER_INPUT);
    }
}
