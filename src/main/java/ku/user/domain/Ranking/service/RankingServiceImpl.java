package ku.user.domain.Ranking.service;

import ku.user.domain.Ranking.dto.response.GetRankingResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class RankingServiceImpl implements RankingService{
    private final RedisTemplate<String, Object> redisTemplate;

    public void updateScore(String characterName, LocalDateTime createdAt, String gameKey, int newScore) {
        ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();

        Double currentScore = zSetOperations.score(gameKey, characterName);

        if (currentScore != null || newScore > currentScore) {
            zSetOperations.add(gameKey, characterName, newScore);

            String createdAtString = createdAt.toString();
            redisTemplate.opsForHash().put(gameKey + "date", characterName, createdAtString);
            return;
        }

        zSetOperations.add(gameKey, characterName, currentScore);

        String createdAtString = createdAt.toString();
        redisTemplate.opsForHash().put(gameKey + "date", characterName, createdAtString);

    }


    public List<GetRankingResponse> getTopRankers(String gameKey, int n) {
        ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();
        Set<Object> ranking = zSetOperations.reverseRange(gameKey, 0, n - 1);
        List<GetRankingResponse> response = new ArrayList<>();

        if (ranking != null) {
            for (Object o : ranking) {
                String characterName = (String) o;  // userId로 캐스팅
                Double score = zSetOperations.score("rhythms", characterName);

                String createdAtString = (String) redisTemplate.opsForHash().get("rhythms" + "date", characterName);
                LocalDateTime createdAt = null;
                if (createdAtString != null) {
                    createdAt = LocalDateTime.parse(createdAtString);
                }

                GetRankingResponse rankingDTO = new GetRankingResponse(characterName, score.intValue(),createdAt);
                response.add(rankingDTO);
            }
        }
        return response;
    }

    public Long getUserRank(String gameKey, String characterName) {
        ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();
        return zSetOperations.reverseRank(gameKey, characterName);
    }
}
