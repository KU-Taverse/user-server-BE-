package ku.user.domain.character.exception;

import static ku.user.global.exception.ErrorCode.CONFLICT_CHARACTER_RESOURCE;

import ku.user.global.exception.CustomException;
import ku.user.global.exception.ErrorCode;

public class AlreadyExistCharacterException extends CustomException {

    public AlreadyExistCharacterException(){
        super("이미 캐릭터가 존재합니다.", ErrorCode.CONFLICT_CHARACTER_RESOURCE);
    }
}
