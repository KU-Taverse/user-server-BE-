package ku.user.domain.ranking.exception;

import ku.user.global.exception.CustomException;
import ku.user.global.exception.ErrorCode;

public class RankingGetFailException extends CustomException {
    public RankingGetFailException() {
        super("상위 랭킹을 조회하는 과정에서 에러가 발생하였습니다.", ErrorCode.RANKING_GET_FAILED);
    }
}