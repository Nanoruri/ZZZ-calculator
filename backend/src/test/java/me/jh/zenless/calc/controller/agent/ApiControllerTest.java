package me.jh.zenless.calc.controller.agent;


import com.fasterxml.jackson.databind.ObjectMapper;
import me.jh.zenless.calc.entity.ElementType;
import me.jh.zenless.calc.entity.RoleType;
import me.jh.zenless.calc.entity.agent.Agent;
import me.jh.zenless.calc.entity.agent.CoreSkill;
import me.jh.zenless.calc.entity.agent.Skill;
import me.jh.zenless.calc.service.agent.AgentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(ApiController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AgentService agentService;

    @Mock
    private Agent agent;
    @Mock
    private Agent agent2;
    @Mock
    private CoreSkill coreSkill;
    @Mock
    private List<Skill> skills;

    @BeforeEach
    public void setUp() {
        coreSkill = new CoreSkill("Test Core Skill", 10, "Test Description", agent);

        skills = List.of(
                new Skill(1, "Test Skill", 1, 12, agent),
                new Skill(2, "Test Skill2", 1, 12, agent),
                new Skill(3, "Test Skill3", 1, 12, agent),
                new Skill(4, "Test Skill4", 1, 12, agent),
                new Skill(5, "Test Skill5", 1, 12, agent)
        );

        agent = new Agent("Test Agent", RoleType.fromKoreanName("지원"), ElementType.fromKoreanName("불"), 5, 100, coreSkill, skills);
        agent2 = new Agent("Test Agent2", RoleType.fromKoreanName("강공"), ElementType.fromKoreanName("에테르"), 5, 100, coreSkill, skills);


    }


    @Test
    public void createAgentSuccessfully() throws Exception {

        String agentJson = objectMapper.writeValueAsString(agent);

        when(agentService.createAgent(any(Agent.class))).thenReturn(true);

        mockMvc.perform(post("/agent/api")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(agentJson))
                .andExpect(status().isOk());
    }

    @Test
    public void createAgentBadRequest() throws Exception {

        String agentJson = objectMapper.writeValueAsString(agent);

        when(agentService.createAgent(any(Agent.class))).thenReturn(false);

        mockMvc.perform(post("/agent/api")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(agentJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getAgentSuccessfully() throws Exception {

        when(agentService.getAgent("Test Agent")).thenReturn(agent);

        mockMvc.perform(get("/agent/api/{name}", "Test Agent"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(agent.getName()));
    }

    @Test
    public void getAgentBadRequest() throws Exception {

        mockMvc.perform(get("/agent/api/{name}", " "))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getAgentsSuccessfully() throws Exception {
        List<Agent> agents = List.of(agent, agent2);

        when(agentService.getAgents()).thenReturn(agents);

        mockMvc.perform(get("/agent/api"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void updateAgentSuccessfully() throws Exception {

        String agentJson = objectMapper.writeValueAsString(agent);

        when(agentService.updateAgent(any(Agent.class))).thenReturn(true);

        mockMvc.perform(put("/agent/api")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(agentJson))
                .andExpect(status().isOk());
    }

    @Test
    public void updateAgentBadRequest() throws Exception {

        String agentJson = objectMapper.writeValueAsString(agent);

        when(agentService.updateAgent(any(Agent.class))).thenReturn(false);

        mockMvc.perform(put("/agent/api")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(agentJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteAgentSuccessfully() throws Exception {

        doNothing().when(agentService).deleteAgent("Test Agent");

        mockMvc.perform(delete("/agent/api/{name}", "Test Agent"))
                .andExpect(status().isNoContent());
    }
}
