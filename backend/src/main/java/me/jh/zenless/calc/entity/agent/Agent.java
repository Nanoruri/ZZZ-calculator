package me.jh.zenless.calc.entity.agent;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.jh.zenless.calc.entity.ElementType;
import me.jh.zenless.calc.entity.RoleType;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "AGENT")
public class Agent {

    @Id
    @Column(name = "AGENT_ID", unique = true)
    private String name;// 이름

    @Enumerated(EnumType.STRING)
    @Column(name = "AGENT_ROLE")
    private RoleType role;// 역할군

    @Enumerated(EnumType.STRING)
    @Column(name = "AGENT_ATTRIBUTE")
    private ElementType elementType; // 속성

    @Column(name = "AGENT_LEVEL")
    //TODO : 음수는 안되게, 최대레벨 제한(60) 걸어야함
    private int level = 1; // 레벨

    @Column(name = "AGENT_EXP")
    private int exp = 0; // 경험치


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CORE_SKILL_ID", unique = true)
    @JsonManagedReference("agent-coreSkill")
    private CoreSkill coreSkill; // 코어 스킬

    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)//TODO: FetchType.LAZY로 변경할 것
    @JsonManagedReference("agent-skill")
    private List<Skill> skills; // 세부 스킬

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "AGENT_STAT_ID")
//    private List<Stat> stats; // 능력치



}
// API = 에이전트를 List로 반환하는 API, 에이전트 정보를 단일로 반환하는 API
// 에이전트를 생성하는 API, 에이전트를 수정하는 API, 에이전트를 삭제하는 API

// API = 재료 아이템을 List로 반환하는 API, 재료 아이템 정보를 단일로 반환하는 API
// 재료 아이템을 생성하는 API, 재료 아이템을 수정하는 API, 재료 아이템을 삭제하는 API

