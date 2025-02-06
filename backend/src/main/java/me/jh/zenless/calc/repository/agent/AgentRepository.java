package me.jh.zenless.calc.repository.agent;

import me.jh.zenless.calc.entity.agent.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<Agent, String> {

}
