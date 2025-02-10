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
@Table(name = "RESOURCE_ITEM")
public class LevelUpResource { // 에이전트 레벨업 재료, 무기 레벨업 재료 엔티티

    @Id
    @Column(name = "RESOURCE_ID", unique = true)
    private String name;// ex) 조사원 기록, 배터리

    @Column(name = "RESOURCE_LEVEL")
    private int experience;// ex)+100

    @Column(name = "RESOURCE_TYPE")
    private String type;// ex) 에이전트 레벨업 재료, 무기 레벨업 재료

}
