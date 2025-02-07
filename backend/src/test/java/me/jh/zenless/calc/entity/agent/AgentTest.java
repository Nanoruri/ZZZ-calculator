package me.jh.zenless.calc.entity.agent;

import me.jh.zenless.calc.entity.ElementType;
import me.jh.zenless.calc.entity.RoleType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class AgentTest {

    @Test
    public void testAgentFields() {
        CoreSkill coreSkill = new CoreSkill();
        Skill skill1 = new Skill();
        Skill skill2 = new Skill();
        List<Skill> skills = Arrays.asList(skill1, skill2);

        Agent agent = new Agent();
        agent.setName("Test Agent");
        agent.setRole(RoleType.BREAK);
        agent.setElementType(ElementType.FIRE);
        agent.setLevel(10);
        agent.setExp(1000);
        agent.setCoreSkill(coreSkill);
        agent.setSkills(skills);

        assertEquals("Test Agent", agent.getName());
        assertEquals(RoleType.BREAK, agent.getRole());
        assertEquals(ElementType.FIRE, agent.getElementType());
        assertEquals(10, agent.getLevel());
        assertEquals(1000, agent.getExp());
        assertEquals(coreSkill, agent.getCoreSkill());
        assertEquals(skills, agent.getSkills());
    }

    @Test
    public void testAgentConstructor() {
        CoreSkill coreSkill = new CoreSkill();
        Skill skill1 = new Skill();
        Skill skill2 = new Skill();
        List<Skill> skills = Arrays.asList(skill1, skill2);

        Agent agent = new Agent("Test Agent", RoleType.BREAK, ElementType.FIRE, 10, 1000, coreSkill, skills);

        assertEquals("Test Agent", agent.getName());
        assertEquals(RoleType.BREAK, agent.getRole());
        assertEquals(ElementType.FIRE, agent.getElementType());
        assertEquals(10, agent.getLevel());
        assertEquals(1000, agent.getExp());
        assertEquals(coreSkill, agent.getCoreSkill());
        assertEquals(skills, agent.getSkills());
    }
}