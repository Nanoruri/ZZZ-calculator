package me.jh.zenless.calc.entity.agent;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SKILL_NO")
    private long id;


    @Column(name = "SKILL_NAME")
    private String name;

    @Column(name = "SKILL_LEVEL")
    //TODO : 음수는 안되게, 최대레벨 제한(11) 걸어야함
    private int level = 1; // 레벨

    @Column(name = "SKILL_MAX_LEVEL")
    private int maxLevel = 12; // 최대 레벨

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AGENT_ID")
    @JsonBackReference("agent-skill")
    private Agent agent; // 스킬을 가지고 있는 캐릭터

}
