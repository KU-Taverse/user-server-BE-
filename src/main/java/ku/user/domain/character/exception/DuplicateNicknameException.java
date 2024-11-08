package ku.user.domain.character.exception;

import static ku.user.global.exception.ErrorCode.INVALID_CHARACTER_INPUT;

import ku.user.global.exception.CustomException;

public class DuplicateNicknameException extends CustomException {

    public DuplicateNicknameException(){
        super("닉네임 중복입니다.",INVALID_CHARACTER_INPUT);
    }
}
