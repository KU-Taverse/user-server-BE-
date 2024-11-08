package ku.user.domain.character.exception;

import ku.user.global.exception.CustomException;
import ku.user.global.exception.ErrorCode;

public class NotFoundCharacterException extends CustomException {

    public NotFoundCharacterException(){
        super(ErrorCode.CHARACTER_NOT_FOUND);
    }
}
