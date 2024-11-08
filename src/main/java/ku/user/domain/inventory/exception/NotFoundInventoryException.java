package ku.user.domain.inventory.exception;

import ku.user.global.exception.CustomException;
import ku.user.global.exception.ErrorCode;

public class NotFoundInventoryException extends CustomException {

    public NotFoundInventoryException(){
        super("인벤토리를 찾을 수 없습니다",ErrorCode.ITEM_NOT_FOUND);
    }
}
