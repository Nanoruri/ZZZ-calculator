package me.jh.zenless.calc.entity.material;

import me.jh.zenless.calc.entity.ElementType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ActiveProfiles("test")
public class BreakthroughResourceTest {

    @Test
    public void createBreakthroughResourceWithValidData() {
        BreakthroughResource breakthroughResource = new BreakthroughResource();
        breakthroughResource.setName("초급 강공 휘장");
        breakthroughResource.setAttribute(ElementType.PHYSICAL);
        breakthroughResource.setType("휘장");
        breakthroughResource.setLevelRangeStart(1);
        breakthroughResource.setLevelRangeEnd(20);

        assertEquals("초급 강공 휘장", breakthroughResource.getName());
        assertEquals(ElementType.PHYSICAL, breakthroughResource.getAttribute());
        assertEquals("휘장", breakthroughResource.getType());
        assertEquals(1, breakthroughResource.getLevelRangeStart());
        assertEquals(20, breakthroughResource.getLevelRangeEnd());
    }

    @Test
    public void createBreakthroughResourceWithNullValues() {
        BreakthroughResource breakthroughResource = new BreakthroughResource();
        breakthroughResource.setName(null);
        breakthroughResource.setAttribute(null);
        breakthroughResource.setType(null);
        breakthroughResource.setLevelRangeStart(0);
        breakthroughResource.setLevelRangeEnd(0);

        assertNull(breakthroughResource.getName());
        assertNull(breakthroughResource.getAttribute());
        assertNull(breakthroughResource.getType());
        assertEquals(0, breakthroughResource.getLevelRangeStart());
        assertEquals(0, breakthroughResource.getLevelRangeEnd());
    }


    @Test
    public void createBreakthroughResourceWithResourceCore() {
        ResourceCore resourceCore = new ResourceCore();
        resourceCore.setName("기본 재료");

        BreakthroughResource breakthroughResource = new BreakthroughResource();
        breakthroughResource.setName("초급 강공 휘장");
        breakthroughResource.setAttribute(ElementType.PHYSICAL);
        breakthroughResource.setType("휘장");
        breakthroughResource.setLevelRangeStart(1);
        breakthroughResource.setLevelRangeEnd(20);
        breakthroughResource.setResourceCore(resourceCore);

        assertEquals("초급 강공 휘장", breakthroughResource.getName());
        assertEquals(ElementType.PHYSICAL, breakthroughResource.getAttribute());
        assertEquals("휘장", breakthroughResource.getType());
        assertEquals(1, breakthroughResource.getLevelRangeStart());
        assertEquals(20, breakthroughResource.getLevelRangeEnd());
        assertEquals("기본 재료", breakthroughResource.getResourceCore().getName());
    }
}