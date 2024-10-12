package ku.user.domain.quest.controller;

import ku.user.domain.quest.domain.UserQuest;
import ku.user.domain.quest.dto.request.PostUserQuestRequest;
import ku.user.domain.quest.dto.response.GetUserQuestsResponse;
import ku.user.domain.quest.dto.response.PostUserQuestResponse;
import ku.user.domain.quest.service.QuestService;
import ku.user.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class QuestController {

    private final QuestService questService;

    @GetMapping("/quests")
    public ApiResponse<GetUserQuestsResponse> checkQuest(@RequestParam String email) {
        UserQuest userQuest = questService.checkAndRefreshQuest(email);
        GetUserQuestsResponse getUserQuestsResponse = GetUserQuestsResponse.toDto(userQuest);
        return new ApiResponse<>(true, getUserQuestsResponse, null);
    }

    @PostMapping("/quests")
    public ApiResponse<PostUserQuestResponse> solveQuest(@RequestParam String email, @RequestBody PostUserQuestRequest postUserQuestRequest) {
        UserQuest userQuest = questService.updateQuestProgress(postUserQuestRequest.getUserQuestMap(), email);
        PostUserQuestResponse postUserQuestResponse = PostUserQuestResponse.toDto(userQuest);
        return new ApiResponse<>(true, postUserQuestResponse, null);
    }


}
