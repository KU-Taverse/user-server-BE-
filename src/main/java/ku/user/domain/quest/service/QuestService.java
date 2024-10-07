package ku.user.domain.quest.service;

import ku.user.domain.quest.dao.QuestRepository;
import ku.user.domain.quest.domain.Quest;
import ku.user.domain.quest.util.QuestUtil;
import ku.user.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestService {

    private final QuestRepository questRepository;
    private final UserService userService;

    @Transactional
    public Quest save(Quest quest){
        return questRepository.save(quest);
    }

    @Transactional
    public Quest findByUserId(Long userId){
        return questRepository.findByUserId(userId).get();
    }

    /**
     * 유저의 퀘스트를 조회한다.(가능하면 갱신한다)
     *
     * @param email
     * @return quest
     */
    @Transactional
    public Quest checkAndRefreshQuest(String email) {
        Long userId = userService.getByEmail(email).getId();
        LocalDate localDate = LocalDate.now();
        Optional<Quest> questOptional = questRepository.findQuestByUserIdAndCompletionDate(userId, localDate);
        return questOptional.orElseGet(() -> createDailyQuest(userId));
    }

    /**
     * 새로운 퀘스트를 생성한다
     * @param userId
     * @return quest
     */
    @Transactional
    public Quest createDailyQuest(Long userId) {
        List<Integer> randomQuestNumber = QuestUtil.getRandomQuests(3);
        Quest quest = Quest.from(userId, randomQuestNumber);
        return save(quest);
    }

    /**
     * 퀘스트를 해결한다
     * @param questIndex 퀘스트 번호
     * @param email
     * @return
     */
    @Transactional
    public Quest solveQuestByEmail(Integer questIndex, String email) {
        Long userId = userService.getByEmail(email).getId();
        Quest findQuest = findByUserId(userId);
        findQuest.solve(questIndex);
        return findQuest;
    }


}
