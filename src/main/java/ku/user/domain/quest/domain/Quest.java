package ku.user.domain.quest.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "quests")
@Getter
public class Quest {

    @Id
    private Long id; //id
    private String Title; //제목
    private String description; //내용
    private int clearCondition; //성공조건
    private int reward; //보상

}
