package ku.user.domain.quest.controller;

import jakarta.ws.rs.PATCH;
import ku.user.domain.quest.domain.Quest;
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
        Quest quest = questService.checkAndRefreshQuest(email);
        GetUserQuestsResponse getUserQuestsResponse = GetUserQuestsResponse.toDto(quest);
        return new ApiResponse<>(true, getUserQuestsResponse, null);
    }

    @PostMapping("/quests")
    public ApiResponse<PostUserQuestResponse> solveQuest(@RequestParam String email, @RequestBody PostUserQuestRequest postUserQuestRequest) {
        Quest quest = questService.solveQuestByEmail(postUserQuestRequest.getQuestIndex(), email);
        PostUserQuestResponse postUserQuestResponse = PostUserQuestResponse.toDto(quest);
        return new ApiResponse<>(true, postUserQuestResponse, null);
    }


}
