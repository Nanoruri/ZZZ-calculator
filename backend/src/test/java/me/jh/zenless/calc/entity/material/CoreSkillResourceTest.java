package me.jh.zenless.calc.entity.material;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ActiveProfiles("test")
public class CoreSkillResourceTest {

    @Test
    public void createCoreSkillResourceWithValidData() {
        CoreSkillResource coreSkillResource = new CoreSkillResource();
        coreSkillResource.setName("형별 통지");
        coreSkillResource.setType("고차원 데이터");

        assertEquals("형별 통지", coreSkillResource.getName());
        assertEquals("고차원 데이터", coreSkillResource.getType());
    }

    @Test
    public void createCoreSkillResourceWithNullValues() {
        CoreSkillResource coreSkillResource = new CoreSkillResource();
        coreSkillResource.setName(null);
        coreSkillResource.setType(null);

        assertNull(coreSkillResource.getName());
        assertNull(coreSkillResource.getType());
    }

    @Test
    public void createCoreSkillResourceWithResourceCore() {
        ResourceCore resourceCore = new ResourceCore();
        resourceCore.setName("핵심스킬 돌파 재료");

        CoreSkillResource coreSkillResource = new CoreSkillResource();
        coreSkillResource.setName("형별 통지");
        coreSkillResource.setType("고차원 데이터");
        coreSkillResource.setResourceCore(resourceCore);

        assertEquals("형별 통지", coreSkillResource.getName());
        assertEquals("고차원 데이터", coreSkillResource.getType());
        assertEquals("핵심스킬 돌파 재료", coreSkillResource.getResourceCore().getName());
    }
}