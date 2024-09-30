package ku.user.domain.Ranking.controller;

import ku.user.domain.Ranking.domain.RhythmScore;
import ku.user.domain.Ranking.dto.request.SaveRhythmRequest;
import ku.user.domain.Ranking.dto.response.GetRankingResponse;
import ku.user.domain.Ranking.dto.response.SaveRhythmResponse;
import ku.user.domain.Ranking.service.RankingService;
import ku.user.domain.Ranking.service.RhythmScoreService;
import ku.user.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class RhythmScoreController {
    private final RhythmScoreService rhythmScoreService;
    private final RankingService rankingService;
    private final RedisTemplate<String, Object> redisTemplate;

    // 저장
    @PostMapping("/rhythms")
    public ApiResponse<SaveRhythmResponse> saveStepping(@RequestBody SaveRhythmRequest saveSteppingRequest){
        RhythmScore rhythmScore = SaveRhythmRequest.toEntity(saveSteppingRequest);
        RhythmScore savedScore = rhythmScoreService.saveScore(rhythmScore);
        SaveRhythmResponse response = SaveRhythmResponse.fromEntity(savedScore);

        return new ApiResponse<>(true, response, null);
    }

    // 랭킹 보이기
    @GetMapping("/rhythms/ranking")
    public ApiResponse<List<GetRankingResponse>> showRanking() {
        ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();
        // 랭킹 조회
        Set<Object> ranking = rankingService.getTopRankers("rhythms", 100);
        List<GetRankingResponse> response = new ArrayList<>();

        if (ranking != null) {
            for (Object o : ranking) {
                String characterName = (String) o;  // userId로 캐스팅
                Double score = zSetOperations.score("rhythms", characterName);

                GetRankingResponse rankingDTO = new GetRankingResponse(characterName, score.intValue());
                response.add(rankingDTO);
            }
        }

        return new ApiResponse<>(true, response, null);
    }

}
