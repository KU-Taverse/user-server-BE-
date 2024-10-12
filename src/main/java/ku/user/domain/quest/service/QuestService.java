package ku.user.domain.quest.service;

import ku.user.domain.quest.dao.UserQuestRepository;
import ku.user.domain.quest.domain.UserQuest;
import ku.user.domain.quest.util.QuestUtil;
import ku.user.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestService {

    private final UserQuestRepository userQuestRepository;
    private final UserService userService;

    @Transactional
    public UserQuest save(UserQuest quest){
        return userQuestRepository.save(quest);
    }

    @Transactional
    public UserQuest findByUserId(Long userId){
        return userQuestRepository.findByUserId(userId).get();
    }

    /**
     * 유저의 퀘스트를 조회한다.(가능하면 갱신한다)
     *
     * @param email
     * @return quest
     */
    @Transactional
    public UserQuest checkAndRefreshQuest(String email) {
        Long userId = userService.getByEmail(email).getId();
        LocalDate localDate = LocalDate.now();
        Optional<UserQuest> questOptional = userQuestRepository.findQuestByUserIdAndCompletionDate(userId, localDate);
        return questOptional.orElseGet(() -> createDailyQuest(userId));
    }

    /**
     * 새로운 퀘스트를 생성한다
     * @param userId
     * @return quest
     */
    @Transactional
    public UserQuest createDailyQuest(Long userId) {
        List<Integer> randomQuestNumber = QuestUtil.getRandomQuests(3);
        UserQuest quest = UserQuest.from(userId, randomQuestNumber);
        return save(quest);
    }

    /**
     * 퀘스트를 해결한다
     * @param questIndex 퀘스트 번호
     * @param email
     * @return
     */
    @Transactional
    public UserQuest solveQuestByEmail(Integer questIndex, String email) {
        Long userId = userService.getByEmail(email).getId();
        UserQuest findQuest = findByUserId(userId);
        findQuest.solve(questIndex);
        return findQuest;
    }


}
