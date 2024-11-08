package ku.user.domain.shop.exception;

import ku.user.global.exception.CustomException;
import ku.user.global.exception.ErrorCode;

public class NotFoundItemException extends CustomException {

    public NotFoundItemException(){
        super(ErrorCode.ITEM_NOT_FOUND);
    }
}
