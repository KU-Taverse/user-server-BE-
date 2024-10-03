package ku.user.domain.character.exception;

import ku.user.global.exception.CustomException;
import ku.user.global.exception.ErrorCode;

public class CurrentMoneyLeakException extends CustomException {
    public CurrentMoneyLeakException() {
        super("현재 재화가 부족합니다.", ErrorCode.INTERNAL_SERVER_ERROR);
    }
}
