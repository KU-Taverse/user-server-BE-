package ku.user.domain.ranking.exception;

import ku.user.global.exception.CustomException;
import ku.user.global.exception.ErrorCode;

public class RedisConnectionFailException extends CustomException {
    public RedisConnectionFailException() {
        super("redis 연결을 실패하였습니다.", ErrorCode.REDIS_CONNECTION_ERROR);
    }
}
