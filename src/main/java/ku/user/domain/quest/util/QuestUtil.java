package ku.user.domain.quest.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class QuestUtil {

    private QuestUtil() {
    }

    public static int QUEST_COUNT = 3;
    private static final ThreadLocalRandom random = ThreadLocalRandom.current();

    public static Integer getRandomQuest() {
        return random.nextInt(QUEST_COUNT);
    }

    public static List<Integer> getRandomQuests(int count) {
        Set<Integer> set = new HashSet<>();
        while(set.size()<count){
            Integer randomQuest = getRandomQuest() + 1;
            set.add(randomQuest);
        }
        return set.stream().toList();
    }
}
