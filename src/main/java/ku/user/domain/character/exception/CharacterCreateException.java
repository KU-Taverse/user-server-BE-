package ku.user.domain.character.exception;

import ku.user.global.exception.CustomException;
import ku.user.global.exception.ErrorCode;
import org.springframework.dao.DataAccessException;

public class CharacterCreateException extends CustomException {

    public CharacterCreateException() {
        super("이미 캐릭터가 있거나 닉네임이 중복입니다.", ErrorCode.INTERNAL_SERVER_ERROR);
    }

}
