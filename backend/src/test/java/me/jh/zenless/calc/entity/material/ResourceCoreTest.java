package me.jh.zenless.calc.entity.material;

import me.jh.zenless.calc.entity.ElementType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ActiveProfiles("test")
public class ResourceCoreTest {

    @Test
    public void createResourceCoreWithValidData() {
        ResourceCore resourceCore = new ResourceCore();
        resourceCore.setName("경험치 재료");

        assertEquals("경험치 재료", resourceCore.getName());
    }

    @Test
    public void createResourceCoreWithNullName() {
        ResourceCore resourceCore = new ResourceCore();
        resourceCore.setName(null);

        assertNull(resourceCore.getName());
    }

    @Test
    public void addLevelUpResourceToResourceCore() {
        ResourceCore resourceCore = new ResourceCore();
        resourceCore.setName("경험치 재료");

        LevelUpResource levelUpResource = new LevelUpResource();
        levelUpResource.setName("조사원 기록");
        levelUpResource.setExperience(100);
        levelUpResource.setType("에이전트 레벨업 재료");
        levelUpResource.setResourceCore(resourceCore);

        resourceCore.getLevelUpResources().add(levelUpResource);

        assertEquals(1, resourceCore.getLevelUpResources().size());
    }

    @Test
    public void addSkillResourceToResourceCore() {
        ResourceCore resourceCore = new ResourceCore();
        resourceCore.setName("스킬 재료");

        SkillResource skillResource = new SkillResource();
        skillResource.setName("기본 칩");
        skillResource.setType(ElementType.FIRE);
        skillResource.setLevelRangeStart(1);
        skillResource.setLevelRangeEnd(20);
        skillResource.setResourceCore(resourceCore);

        resourceCore.getSkillResources().add(skillResource);

        assertEquals(1, resourceCore.getSkillResources().size());
    }

    @Test
    public void addCoreSkillResourceToResourceCore() {
        ResourceCore resourceCore = new ResourceCore();
        resourceCore.setName("고차원 데이터");

        CoreSkillResource coreSkillResource = new CoreSkillResource();
        coreSkillResource.setName("형별 통지");
        coreSkillResource.setType("고차원 데이터");
        coreSkillResource.setResourceCore(resourceCore);

        resourceCore.getCoreSkillResources().add(coreSkillResource);

        assertEquals(1, resourceCore.getCoreSkillResources().size());
    }

    @Test
    public void addBreakthroughResourceToResourceCore() {
        ResourceCore resourceCore = new ResourceCore();
        resourceCore.setName("돌파 재료");

        BreakthroughResource breakthroughResource = new BreakthroughResource();
        breakthroughResource.setName("초급 강공 휘장");
        breakthroughResource.setAttribute(ElementType.PHYSICAL);
        breakthroughResource.setType("휘장");
        breakthroughResource.setLevelRangeStart(1);
        breakthroughResource.setLevelRangeEnd(20);
        breakthroughResource.setResourceCore(resourceCore);

        resourceCore.getBreakthroughResources().add(breakthroughResource);

        assertEquals(1, resourceCore.getBreakthroughResources().size());
    }
}