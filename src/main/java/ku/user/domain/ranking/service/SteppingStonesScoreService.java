package ku.user.domain.ranking.service;

import ku.user.domain.ranking.domain.SteppingStonesScore;

import java.time.LocalDateTime;
import java.util.List;

public interface SteppingStonesScoreService {
    // 생성
    SteppingStonesScore saveScore(SteppingStonesScore steppingStonesScore);

    // 조회(해당 캐릭터의 모든 기록)
    List<SteppingStonesScore> findScoresByNickName(String nickName);

    // 조회(해당 캐릭터의 특정 날짜 기록)
    List<SteppingStonesScore> findScoresByNickNameAndDate(String nickName, LocalDateTime startDate, LocalDateTime endDate);

    // 캐릭터 삭제
    void updateScoreStatus(String nickName);

    // 해당 캐릭터의 최고 기록
    SteppingStonesScore findHighestScoreByNickName(String nickName);
}
