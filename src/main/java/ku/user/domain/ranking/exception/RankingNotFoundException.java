package ku.user.domain.ranking.exception;

import ku.user.global.exception.CustomException;
import ku.user.global.exception.ErrorCode;

public class RankingNotFoundException extends CustomException {
    public RankingNotFoundException() {
        super("랭킹 정보를 가져오지 못 했습니다.", ErrorCode.RANKING_NOT_FOUND);
    }
}
