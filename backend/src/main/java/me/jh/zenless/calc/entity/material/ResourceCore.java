package me.jh.zenless.calc.entity.material;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "COMMON_RESOURCE")
public class ResourceCore {

	@Id
	@Column(name = "RESOURCE_CORE_ID", unique = true)
	private String name; // 어디에 필요한 재료인지 ex) 경험치 재료, 스킬 재료, 돌파재료...

	@OneToMany(mappedBy = "resourceCore", fetch = FetchType.LAZY)
	private Set<LevelUpResource> levelUpResources = new HashSet<>();

	@OneToMany(mappedBy = "resourceCore", fetch = FetchType.LAZY)
	private Set<SkillResource> skillResources = new HashSet<>();

	@OneToMany(mappedBy = "resourceCore", fetch = FetchType.LAZY)
	private Set<CoreSkillResource> coreSkillResources = new HashSet<>();

	@OneToMany(mappedBy = "resourceCore", fetch = FetchType.LAZY)
	private Set<BreakthroughResource> breakthroughResources = new HashSet<>();
}