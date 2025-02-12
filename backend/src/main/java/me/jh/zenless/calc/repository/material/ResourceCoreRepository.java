package me.jh.zenless.calc.repository.material;

import me.jh.zenless.calc.entity.material.ResourceCore;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceCoreRepository extends JpaRepository<ResourceCore, String> {
	@Override
	@EntityGraph(attributePaths = {"levelUpResources", "skillResources", "coreSkillResources", "breakthroughResources"})
	List<ResourceCore> findAll();

}
