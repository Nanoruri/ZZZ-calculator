package me.jh.zenless.calc.entity.agent;

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
@Table(name = "CORE_SKILL")
public class CoreSkill {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CORE_SKILL_NO")
    private long seq; // 코어 스킬 번호

    @Id
    @Column(name = "CORE_SKILL_ID", unique = true)
    private String name;

    @Column(name = "CORE_SKILL_LEVEL")
    private int level = 1; // 레벨

    @Column(name = "CORE_SKILL_DISCRIPTION")
    private String discription; // 핵심 스킬 설명

    @OneToOne(mappedBy = "coreSkill")
    private Agent agent; // 코어 스킬을 가지고 있는 캐릭터
}
