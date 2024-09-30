package ku.user.domain.Ranking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RankingServiceImpl implements RankingService{
    private final RedisTemplate<String, Object> redisTemplate;
    private static final String GAME1_KEY = "rhythms";
    private static final String GAME2_KEY = "stepping_stones";

    public void updateScore(String characterName, LocalDateTime createdAt, String gameKey, int newScore) {
        ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();

        Double currentScore = zSetOperations.score(gameKey, characterName);

        if (currentScore == null || newScore > currentScore) {
            // ZSet에 최고 기록으로 업데이트
            zSetOperations.add(gameKey, characterName, newScore);

            // 날짜는 따로 저장
            redisTemplate.opsForHash().put(gameKey+"date", characterName, createdAt);
        }
    }


    public Set<Object> getTopRankers(String gameKey, int n) {
        ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();
        return zSetOperations.reverseRange(gameKey, 0, n - 1);
    }

    public Long getUserRank(String gameKey, String characterName) {
        ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();
        return zSetOperations.reverseRank(gameKey, characterName);
    }
}
