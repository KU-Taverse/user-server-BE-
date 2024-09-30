package ku.user.domain.Ranking.controller;

import ku.user.domain.Ranking.domain.SteppingStonesScore;
import ku.user.domain.Ranking.dto.request.SaveSteppingRequest;
import ku.user.domain.Ranking.dto.response.GetRankingResponse;
import ku.user.domain.Ranking.dto.response.SaveSteppingResponse;
import ku.user.domain.Ranking.service.RankingService;
import ku.user.domain.Ranking.service.SteppingStonesScoreService;
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
public class SteppingStonesScoreController {
    private final SteppingStonesScoreService steppingStonesScoreService;
    private final RankingService rankingService;
    // 저장
    @PostMapping("/stepping")
    public ApiResponse<SaveSteppingResponse> saveStepping(@RequestBody SaveSteppingRequest saveSteppingRequest){
        SteppingStonesScore steppingStonesScore = SaveSteppingRequest.toEntity(saveSteppingRequest);
        SteppingStonesScore savedScore = steppingStonesScoreService.saveScore(steppingStonesScore);
        SaveSteppingResponse response = SaveSteppingResponse.fromEntity(savedScore);

        return new ApiResponse<>(true, response, null);
    }

    @GetMapping("/stepping/ranking")
    public ApiResponse<List<GetRankingResponse>> showRanking() {
        // 랭킹 조회
        List<GetRankingResponse> response = rankingService.getTopRankers("stepping_stones", 100);

        return new ApiResponse<>(true, response, null);
    }
}
