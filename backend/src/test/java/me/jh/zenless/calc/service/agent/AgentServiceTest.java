package me.jh.zenless.calc.service.agent;

import me.jh.zenless.calc.entity.ElementType;
import me.jh.zenless.calc.entity.RoleType;
import me.jh.zenless.calc.entity.agent.Agent;
import me.jh.zenless.calc.entity.agent.CoreSkill;
import me.jh.zenless.calc.entity.agent.Skill;
import me.jh.zenless.calc.entity.agent.builder.AgentStep;
import me.jh.zenless.calc.repository.agent.AgentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class AgentServiceTest {

    @Mock
    private AgentRepository agentRepository;

    @Mock
    private Agent agent;
    @Mock
    private CoreSkill coreSkill;
    @Mock
    private List<Skill> skills;

    @InjectMocks
    private AgentService agentService;

    @BeforeEach
    public void setUp() {
        coreSkill = new CoreSkill("Test CoreSkill", 1, "Test Description", agent);

        skills = List.of(
                new Skill(1L, "Test Skill", 1, 12, agent),
                new Skill(2L, "Test Skill2", 1, 12, agent),
                new Skill(3L, "Test Skill3", 1, 12, agent),
                new Skill(4L, "Test Skill4", 1, 12, agent),
                new Skill(5L, "Test Skill5", 1, 12, agent)
        );

        agent = AgentStep.builder()
                .name("Test Agent")
                .role(RoleType.fromKoreanName("지원"))
                .elementType(ElementType.fromKoreanName("불"))
                .level(5)
                .exp(100)
                .coreSkill(coreSkill)
                .skills(skills)
                .build();

    }

    @Test
    public void createAgentSuccessfully() {
        when(agentRepository.save(any(Agent.class))).thenReturn(agent);

        boolean result = agentService.createAgent(agent);

        assertTrue(result);
        verify(agentRepository, times(1)).save(any(Agent.class));
    }

    @Test
    public void getAgentSuccessfully() {
        when(agentRepository.findById("Test Agent")).thenReturn(Optional.of(agent));

        Agent result = agentService.getAgent("Test Agent");

        assertNotNull(result);
        assertEquals("Test Agent", result.getName());
    }

    @Test
    public void getAgentNotFound() {
        when(agentRepository.findById("Nonexistent Agent")).thenReturn(Optional.empty());

        Agent result = agentService.getAgent("Nonexistent Agent");

        assertNull(result);
    }

    @Test
    public void deleteAgentSuccessfully() {
        when(agentRepository.findById("Test Agent")).thenReturn(Optional.of(agent));

        agentService.deleteAgent("Test Agent");

        verify(agentRepository, times(1)).delete(agent);
    }

    @Test
    public void updateAgentSuccessfully() {
        Agent existingAgent = agent;

        Agent updateAgent = AgentStep.builder()
                .name("Test Agent")
                .role(RoleType.fromKoreanName("강공"))
                .elementType(ElementType.fromKoreanName("에테르"))
                .level(10)
                .exp(200)
                .coreSkill(coreSkill)
                .skills(skills)
                .build();

        when(agentRepository.findById("Test Agent")).thenReturn(Optional.of(existingAgent));

        boolean result = agentService.updateAgent(updateAgent);

        assertTrue(result);
        verify(agentRepository, times(1)).save(existingAgent);
    }

    @Test
    public void updateAgentNotFound() {
        Agent updateAgent = new Agent();
        updateAgent.setName("Nonexistent Agent");

        when(agentRepository.findById("Nonexistent Agent")).thenReturn(Optional.empty());

        boolean result = agentService.updateAgent(updateAgent);

        assertFalse(result);
        verify(agentRepository, never()).save(any(Agent.class));
    }


    @Test
    public void getAllAgentsSuccessfully() {
        List<Agent> agents = List.of(new Agent(), new Agent());

        when(agentRepository.findAll()).thenReturn(agents);

        List<Agent> result = agentService.getAgents();

        assertEquals(2, result.size());
    }

    @Test
    public void getAllAgentsEmptyList() {
        when(agentRepository.findAll()).thenReturn(List.of());

        List<Agent> result = agentService.getAgents();

        assertTrue(result.isEmpty());
    }

}