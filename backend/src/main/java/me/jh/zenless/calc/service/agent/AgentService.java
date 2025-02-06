package me.jh.zenless.calc.service.agent;

import me.jh.zenless.calc.entity.ElementType;
import me.jh.zenless.calc.entity.RoleType;
import me.jh.zenless.calc.entity.agent.Agent;
import me.jh.zenless.calc.repository.agent.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentService {

    private final AgentRepository agentRepository;


    @Autowired
    public AgentService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }


    public boolean createAgent(Agent agent) {
        Agent newAgent = new Agent();
        newAgent.setName(agent.getName());
        newAgent.setRole(convertRoleType(agent.getRole().name()));
        newAgent.setElementType(convertElementType(agent.getElementType().name()));
        newAgent.setCoreSkill(agent.getCoreSkill());
        newAgent.setSkills(agent.getSkills());

        agentRepository.save(newAgent);

        return true;
    }

    // 에이전트 정보를 반환
    public Agent getAgent(String name) {
        return agentRepository.findById(name).orElse(null);
    }


    //여러 에이전트 정보를 리스트로 반환
    public List<Agent> getAgents() {
        return agentRepository.findAll();
    }

    public Agent deleteAgent(String name) {
        Agent agent = agentRepository.findById(name).orElse(null);
        if (agent != null) {
            agentRepository.delete(agent);
        }
        return agent;
    }

    public boolean updateAgent(Agent updateAgent) {
        try {
            agentRepository.findById(updateAgent.getName()).ifPresentOrElse(agent -> {
                agent.setRole(convertRoleType(updateAgent.getRole().name()));
                agent.setElementType(convertElementType(updateAgent.getElementType().name()));
                agent.setCoreSkill(updateAgent.getCoreSkill());
                agent.setSkills(updateAgent.getSkills());

                agentRepository.save(agent);
            }, () -> {
                throw new IllegalArgumentException("해당 에이전트가 존재하지 않습니다.");
            });
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private RoleType convertRoleType(String koreanName) {
        return RoleType.fromKoreanName(koreanName);
    }

    private ElementType convertElementType(String koreanName) {
        return ElementType.fromKoreanName(koreanName);
    }

}
