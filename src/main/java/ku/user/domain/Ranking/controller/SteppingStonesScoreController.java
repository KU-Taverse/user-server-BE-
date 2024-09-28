package ku.user.domain.Ranking.controller;

import ku.user.domain.Ranking.domain.SteppingStonesScore;
import ku.user.domain.Ranking.dto.request.SaveSteppingRequest;
import ku.user.domain.Ranking.dto.response.SaveSteppingResponse;
import ku.user.domain.Ranking.service.SteppingStonesScoreService;
import ku.user.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SteppingStonesScoreController {
    private final SteppingStonesScoreService steppingStonesScoreService;
    // 저장
    @PostMapping("/stepping")
    public ApiResponse<SaveSteppingResponse> saveStepping(@RequestBody SaveSteppingRequest saveSteppingRequest){
        SteppingStonesScore steppingStonesScore = SaveSteppingRequest.toEntity(saveSteppingRequest);
        SteppingStonesScore savedScore = steppingStonesScoreService.saveScore(steppingStonesScore);
        SaveSteppingResponse response = SaveSteppingResponse.fromEntity(savedScore);

        return new ApiResponse<>(true, response, null);
    }
}
