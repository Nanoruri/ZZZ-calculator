package me.jh.zenless.calc.entity.material;


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
@Table(name = "LEVEL_UP_RESOURCE")
public class LevelUpResource { // 에이전트 레벨업 재료, 무기 레벨업 재료 엔티티

    @Id
    @Column(name = "LEVEL_UP_RESOURCE_ID", unique = true)
    private String name;// ex) 조사원 기록, 배터리

    @Column(name = "LEVEL_UP_RESOURCE_EXP")
    private int experience;// ex)+100

    @Column(name = "LEVEL_UP_RESOURCE_TYPE")
    private String type;// ex) 에이전트 레벨업 재료, 무기 레벨업 재료

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESOURCE_CORE_ID")
    @JsonBackReference
    private ResourceCore resourceCore;
}
