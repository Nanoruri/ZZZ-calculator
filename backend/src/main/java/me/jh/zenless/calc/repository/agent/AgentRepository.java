package me.jh.zenless.calc.repository.agent;

import me.jh.zenless.calc.entity.agent.Agent;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgentRepository extends JpaRepository<Agent, String> {

    @Override
    @EntityGraph(attributePaths = {"coreSkill", "skills"})//fetch join을 이용하게 해주는 어노테이션
    List<Agent> findAll();
}
