package me.jh.zenless.calc.entity.material;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "COMMON_RESOURCE")
public class ResourceCore {

    @Id
    @Column(name = "RESOURCE_CORE_ID")
    private String name; // 어디에 필요한 재료인지 ex) 경험치 재료, 스킬 재료, 돌파재료...

    @OneToMany(mappedBy = "resourceCore", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<LevelUpResource> levelUpResources = new HashSet<>();

    @OneToMany(mappedBy = "resourceCore", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<SkillResource> skillResources = new HashSet<>();

    @OneToMany(mappedBy = "resourceCore", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<CoreSkillResource> coreSkillResources = new HashSet<>();

    @OneToMany(mappedBy = "resourceCore", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<BreakthroughResource> breakthroughResources = new HashSet<>();
}