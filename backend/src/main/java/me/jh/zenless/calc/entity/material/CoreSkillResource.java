package me.jh.zenless.calc.entity.material;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CORE_SKILL_RESOURCE")
public class CoreSkillResource {

    @Id
    @Column(name = "CORE_SKILL_RESOURCE_ID", unique = true)
    private String name; // ex)형별 통지, 진홍색 공포, 피바람의 손아귀, 활력구동..

    @Column(name = "CORE_SKILL_RESOURCE_TYPE")
    private String type;// ex)고차원 데이터, 노토리우스 재료

}
