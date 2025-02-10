package me.jh.zenless.calc.entity.material;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.jh.zenless.calc.entity.ElementType;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SKILL_RESOURCE")
public class SkillResource {

    @Id
    @Column(name = "SKILL_RESOURCE_ID", unique = true)
    private String name; // ex) 기본 칩, 초급 칩, 중급 칩, 고급 칩

    @Column(name = "SKILL_RESOURCE_TYPE")
    @Enumerated(EnumType.STRING)
    private ElementType type; // 속성

    @Column(name = "SKILL_RESOURCE_START_LEVEL")
    private int levelRangeStart; // 레벨 시작 (예: 1)

    @Column(name = "SKILL_RESOURCE_END_LEVEL")
    private int levelRangeEnd; // 레벨 끝 (예: 20)
}
