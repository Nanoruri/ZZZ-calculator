package me.jh.zenless.calc.entity.material;

import me.jh.zenless.calc.entity.ElementType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ActiveProfiles("test")
public class SkillResourceTest {

    @Test
    public void createSkillResourceWithValidData() {
        SkillResource skillResource = new SkillResource();
        skillResource.setName("기본 칩");
        skillResource.setType(ElementType.FIRE);
        skillResource.setLevelRangeStart(1);
        skillResource.setLevelRangeEnd(20);

        assertEquals("기본 칩", skillResource.getName());
        assertEquals(ElementType.FIRE, skillResource.getType());
        assertEquals(1, skillResource.getLevelRangeStart());
        assertEquals(20, skillResource.getLevelRangeEnd());
    }

    @Test
    public void createSkillResourceWithNullValues() {
        SkillResource skillResource = new SkillResource();
        skillResource.setName(null);
        skillResource.setType(null);
        skillResource.setLevelRangeStart(0);
        skillResource.setLevelRangeEnd(0);

        assertNull(skillResource.getName());
        assertNull(skillResource.getType());
        assertEquals(0, skillResource.getLevelRangeStart());
        assertEquals(0, skillResource.getLevelRangeEnd());
    }

    @Test
    public void createSkillResourceWithResourceCore() {
        ResourceCore resourceCore = new ResourceCore();
        resourceCore.setName("기본 재료");

        SkillResource skillResource = new SkillResource();
        skillResource.setName("기본 칩");
        skillResource.setType(ElementType.FIRE);
        skillResource.setLevelRangeStart(1);
        skillResource.setLevelRangeEnd(20);
        skillResource.setResourceCore(resourceCore);

        assertEquals("기본 칩", skillResource.getName());
        assertEquals(ElementType.FIRE, skillResource.getType());
        assertEquals(1, skillResource.getLevelRangeStart());
        assertEquals(20, skillResource.getLevelRangeEnd());
        assertEquals("기본 재료", skillResource.getResourceCore().getName());
    }

    @Test
    public void createSkillResourceAllArgsConstructor() {
        ResourceCore resourceCore = new ResourceCore();
        resourceCore.setName("기본 재료");

        SkillResource skillResource = new SkillResource("기본 칩", ElementType.FIRE, 1, 20, resourceCore);

        assertEquals("기본 칩", skillResource.getName());
        assertEquals(ElementType.FIRE, skillResource.getType());
        assertEquals(1, skillResource.getLevelRangeStart());
        assertEquals(20, skillResource.getLevelRangeEnd());
        assertEquals("기본 재료", skillResource.getResourceCore().getName());
    }
}