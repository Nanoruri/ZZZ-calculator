package me.jh.zenless.calc.entity.material;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ActiveProfiles("test")
public class LevelUpResourceTest {

	@Test
	public void createLevelUpResourceWithValidData() {
		ResourceCore resourceCore = new ResourceCore();
		resourceCore.setName("레벨업 재료");

		LevelUpResource levelUpResource = new LevelUpResource();
		levelUpResource.setName("조사원 기록");
		levelUpResource.setExperience(100);
		levelUpResource.setType("에이전트 레벨업 재료");
		levelUpResource.setResourceCore(resourceCore);

		assertEquals("조사원 기록", levelUpResource.getName());
		assertEquals(100, levelUpResource.getExperience());
		assertEquals("에이전트 레벨업 재료", levelUpResource.getType());
		assertEquals(resourceCore, levelUpResource.getResourceCore());
	}

	@Test
	public void createLevelUpResourceWithNullValues() {
		LevelUpResource levelUpResource = new LevelUpResource();
		levelUpResource.setName(null);
		levelUpResource.setExperience(0);
		levelUpResource.setType(null);

		assertNull(levelUpResource.getName());
		assertEquals(0, levelUpResource.getExperience());
		assertNull(levelUpResource.getType());
	}

	@Test
	public void createLevelUpResourceAllArgsConstructor() {
		ResourceCore resourceCore = new ResourceCore();
		resourceCore.setName("레벨업 재료");

		LevelUpResource levelUpResource = new LevelUpResource("조사원 기록", 100, "에이전트 레벨업 재료", resourceCore);

		assertEquals("조사원 기록", levelUpResource.getName());
		assertEquals(100, levelUpResource.getExperience());
		assertEquals("에이전트 레벨업 재료", levelUpResource.getType());
		assertEquals(resourceCore, levelUpResource.getResourceCore());
	}

}