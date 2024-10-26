package ku.user.domain.ranking.exception;

import ku.user.global.exception.CustomException;
import ku.user.global.exception.ErrorCode;

public class RankingUpdateFailException extends CustomException {
    public RankingUpdateFailException() {
        super("랭킹 업데이트를 실패하였습니다.", ErrorCode.RANKING_UPDATE_FAILED);
    }
}
