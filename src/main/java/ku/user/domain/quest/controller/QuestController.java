package ku.user.domain.quest.controller;

import jakarta.ws.rs.PATCH;
import ku.user.domain.quest.domain.Quest;
import ku.user.domain.quest.dto.response.GetUserQuestsResponse;
import ku.user.domain.quest.service.QuestService;
import ku.user.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QuestController {

    private final QuestService questService;

    @GetMapping("/quests")
    public ApiResponse<GetUserQuestsResponse> checkQuest(@RequestParam String email){
        Quest quest = questService.checkAndRefreshQuest(email);
        GetUserQuestsResponse getUserQuestsResponse = GetUserQuestsResponse.toDto(quest);
        return new ApiResponse<>(true, getUserQuestsResponse, null);
    }





}
