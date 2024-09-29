package ku.user.domain.Ranking.controller;

import ku.user.domain.Ranking.domain.RhythmScore;
import ku.user.domain.Ranking.domain.SteppingStonesScore;
import ku.user.domain.Ranking.dto.request.SaveRhythmRequest;
import ku.user.domain.Ranking.dto.request.SaveSteppingRequest;
import ku.user.domain.Ranking.dto.response.SaveRhythmResponse;
import ku.user.domain.Ranking.dto.response.SaveSteppingResponse;
import ku.user.domain.Ranking.service.RhythmScoreService;
import ku.user.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RhythmScoreController {
    private final RhythmScoreService rhythmScoreService;

    // 저장
    @PostMapping("/rhythm")
    public ApiResponse<SaveRhythmResponse> saveStepping(@RequestBody SaveRhythmRequest saveSteppingRequest){
        RhythmScore rhythmScore = SaveRhythmRequest.toEntity(saveSteppingRequest);
        RhythmScore savedScore = rhythmScoreService.saveScore(rhythmScore);
        SaveRhythmResponse response = SaveRhythmResponse.fromEntity(savedScore);

        return new ApiResponse<>(true, response, null);
    }


}
