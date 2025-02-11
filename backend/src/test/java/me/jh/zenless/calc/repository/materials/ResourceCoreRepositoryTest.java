package me.jh.zenless.calc.repository.materials;

import me.jh.zenless.calc.entity.material.*;
import me.jh.zenless.calc.repository.agent.ResourceCoreRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ResourceCoreRepositoryTest {

    @Autowired
    private ResourceCoreRepository resourceCoreRepository;

    @Test
    public void findAllLoadsRelatedResources() {
        ResourceCore resourceCore = new ResourceCore();
        resourceCore.setName("Test Resource");

        LevelUpResource levelUpResource = new LevelUpResource();
        levelUpResource.setResourceCore(resourceCore);
        resourceCore.setLevelUpResources(Set.of(levelUpResource));

        SkillResource skillResource = new SkillResource();
        skillResource.setResourceCore(resourceCore);
        resourceCore.setSkillResources(Set.of(skillResource));

        CoreSkillResource coreSkillResource = new CoreSkillResource();
        coreSkillResource.setResourceCore(resourceCore);
        resourceCore.setCoreSkillResources(Set.of(coreSkillResource));

        BreakthroughResource breakthroughResource = new BreakthroughResource();
        breakthroughResource.setResourceCore(resourceCore);
        resourceCore.setBreakthroughResources(Set.of(breakthroughResource));

        resourceCoreRepository.save(resourceCore);

        List<ResourceCore> resourceCores = resourceCoreRepository.findAll();
        assertThat(resourceCores).isNotEmpty();
        assertThat(resourceCores.get(0).getLevelUpResources()).isNotEmpty();
        assertThat(resourceCores.get(0).getSkillResources()).isNotEmpty();
        assertThat(resourceCores.get(0).getCoreSkillResources()).isNotEmpty();
        assertThat(resourceCores.get(0).getBreakthroughResources()).isNotEmpty();
    }

    @Test
    public void findAllReturnsEmptyListWhenNoResourceCores() {
        List<ResourceCore> resourceCores = resourceCoreRepository.findAll();
        assertThat(resourceCores).isEmpty();
    }
}