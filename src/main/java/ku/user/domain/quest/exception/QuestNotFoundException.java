package ku.user.domain.quest.exception;

import ku.user.global.exception.CustomException;
import ku.user.global.exception.ErrorCode;

public class QuestNotFoundException extends CustomException {
    public QuestNotFoundException() {
        super(ErrorCode.QUEST_NOT_FOUND);
    }
}
