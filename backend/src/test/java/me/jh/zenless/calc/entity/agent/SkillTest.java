package me.jh.zenless.calc.entity.agent;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class SkillTest {

    @Test
    public void skillFields() {
        Agent agent = new Agent();
        Skill skill = new Skill(1L, "Test Skill", 5, 12, agent);

        assertEquals(1L, skill.getId());
        assertEquals("Test Skill", skill.getName());
        assertEquals(5, skill.getLevel());
        assertEquals(12, skill.getMaxLevel());
        assertEquals(agent, skill.getAgent());
    }

    @Test
    public void skillConstructor() {
        Skill skill = new Skill();
        skill.setId(1L);
        skill.setName("Test Skill");
        skill.setLevel(5);
        skill.setMaxLevel(12);
        Agent agent = new Agent();
        skill.setAgent(agent);

        assertEquals(1L, skill.getId());
        assertEquals("Test Skill", skill.getName());
        assertEquals(5, skill.getLevel());
        assertEquals(12, skill.getMaxLevel());
        assertEquals(agent, skill.getAgent());
    }

    @Test
    public void skillDefaultValues() {
        Skill skill = new Skill();
        assertEquals(1, skill.getLevel());
        assertEquals(12, skill.getMaxLevel());
    }

    @Test
    public void skillNameNull() {
        Skill skill = new Skill();
        skill.setName(null);
        assertNull(skill.getName());
    }

}