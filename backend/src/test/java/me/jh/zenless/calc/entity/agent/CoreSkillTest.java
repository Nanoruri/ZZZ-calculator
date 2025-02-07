package me.jh.zenless.calc.entity.agent;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class CoreSkillTest {

    @Test
    public void testCoreSkillFields() {
        Agent agent = new Agent();
        CoreSkill coreSkill = new CoreSkill("Test Skill", 5, "Test Description", agent);


        assertEquals("Test Skill", coreSkill.getName());
        assertEquals(5, coreSkill.getLevel());
        assertEquals("Test Description", coreSkill.getDiscription());
        assertEquals(agent, coreSkill.getAgent());
    }

    @Test
    public void testCoreSkillConstructor() {
        CoreSkill coreSkill = new CoreSkill();

        coreSkill.setName("Test Skill");
        coreSkill.setLevel(5);
        coreSkill.setDiscription("Test Description");
        Agent agent = new Agent();
        coreSkill.setAgent(agent);

        assertEquals("Test Skill", coreSkill.getName());
        assertEquals(5, coreSkill.getLevel());
        assertEquals("Test Description", coreSkill.getDiscription());
        assertEquals(agent, coreSkill.getAgent());
    }
}