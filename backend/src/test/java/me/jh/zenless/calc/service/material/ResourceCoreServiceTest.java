package me.jh.zenless.calc.service.material;

import me.jh.zenless.calc.entity.ElementType;
import me.jh.zenless.calc.entity.material.*;
import me.jh.zenless.calc.repository.agent.ResourceCoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ResourceCoreServiceTest {

    @Mock
    private ResourceCoreRepository resourceCoreRepository;

    @InjectMocks
    private ResourceCoreService resourceCoreService;

    @Mock
    private ResourceCore resourceCore;

    @Mock
    private BreakthroughResource breakthroughResource;

    @Mock
    private CoreSkillResource coreSkillResource;

    @Mock
    private LevelUpResource levelUpResource;

    @Mock
    private SkillResource skillResource;


    @BeforeEach
    public void setUp() {
        resourceCore = new ResourceCore();
        resourceCore.setName("경험치 재료");


        breakthroughResource = new BreakthroughResource();
        breakthroughResource.setName("초급 강공 휘장");
        breakthroughResource.setAttribute(ElementType.PHYSICAL);
        breakthroughResource.setType("부품");
        breakthroughResource.setLevelRangeStart(1);
        breakthroughResource.setLevelRangeEnd(20);
        breakthroughResource.setResourceCore(resourceCore);

        coreSkillResource = new CoreSkillResource();
        coreSkillResource.setName("형벌통지");
        coreSkillResource.setType("고차원 데이터");
        coreSkillResource.setResourceCore(resourceCore);

        levelUpResource = new LevelUpResource();
        levelUpResource.setName("초급 조사원 기록");
        levelUpResource.setExperience(100);
        levelUpResource.setType("에이전트 레벨업 재료");

        skillResource = new SkillResource();
        skillResource.setName("기본 칩");
        skillResource.setType(ElementType.PHYSICAL);
        skillResource.setLevelRangeStart(1);
        skillResource.setLevelRangeEnd(20);
        skillResource.setResourceCore(resourceCore);


    }


    @Test
    public void createResourceWithValidResources() {
        resourceCore.setBreakthroughResources(Set.of(breakthroughResource));

        when(resourceCoreRepository.save(any(ResourceCore.class))).thenReturn(resourceCore);

        boolean result = resourceCoreService.createResourceCore(resourceCore);

        assertTrue(result);
        verify(resourceCoreRepository).save(any(ResourceCore.class));
    }

//    @Test
//    public void createResourceWithEmptyResources() {
//        resourceCore.setBreakthroughResources(Collections.emptySet());
//        resourceCore.setLevelUpResources(Collections.emptySet());
//        resourceCore.setSkillResources(Collections.emptySet());
//        resourceCore.setCoreSkillResources(Collections.emptySet());
//
//        boolean result = resourceCoreService.createResourceCore(resourceCore);
//
//        assertFalse(result);
//        verify(resourceCoreRepository, never()).save(resourceCore);
//    }

    @Test// 특정 ResourceCore 조회 성공
    public void getResourceCoreById() {
        when(resourceCoreRepository.findById(resourceCore.getName())).thenReturn(Optional.of(resourceCore));

        ResourceCore result = resourceCoreService.getResourceCoreById(resourceCore.getName());

        assertEquals(resourceCore, result);
        verify(resourceCoreRepository).findById(resourceCore.getName());
    }

    @Test
    public void getAllResourceCores() {
        resourceCore.setLevelUpResources(Set.of(levelUpResource));
        resourceCore.setSkillResources(Set.of(skillResource));
        resourceCore.setCoreSkillResources(Set.of(coreSkillResource));
        resourceCore.setBreakthroughResources(Set.of(breakthroughResource));

        List<ResourceCore> resourceCores = List.of(resourceCore);
        when(resourceCoreRepository.findAll()).thenReturn(resourceCores);

        List<ResourceCore> result = resourceCoreService.getAllResourceCores();

        assertEquals(resourceCores, result);
        verify(resourceCoreRepository).findAll();
    }

    @Test
    public void updateResourceCore() {
        ResourceCore updatedResourceCore = new ResourceCore();
        updatedResourceCore.setName("경험치 재료");
        updatedResourceCore.setLevelUpResources(Set.of(levelUpResource));

        when(resourceCoreRepository.findById(resourceCore.getName())).thenReturn(Optional.of(resourceCore));
        when(resourceCoreRepository.save(resourceCore)).thenReturn(resourceCore);

        ResourceCore result = resourceCoreService.updateResourceCore(resourceCore.getName(), updatedResourceCore);

        assertEquals(updatedResourceCore.getLevelUpResources(), result.getLevelUpResources());
        verify(resourceCoreRepository).findById(resourceCore.getName());
        verify(resourceCoreRepository).save(resourceCore);
    }

    @Test
    public void updateResourceCoreNotFound() {
        when(resourceCoreRepository.findById(resourceCore.getName())).thenReturn(Optional.empty());

        ResourceCore result = resourceCoreService.updateResourceCore(resourceCore.getName(), resourceCore);

        assertNull(result);
        verify(resourceCoreRepository).findById(resourceCore.getName());
        verify(resourceCoreRepository, never()).save(any(ResourceCore.class));
    }


    @Test
    public void testDeleteLevelUpResource_Success() {
        String resourceType = "levelUp";
        LevelUpResource levelUpResource = new LevelUpResource("LevelUp1", 100, "testType", resourceCore);
        resourceCore.getLevelUpResources().add(levelUpResource); // 자원 추가


        when(resourceCoreRepository.findById(resourceCore.getName())).thenReturn(Optional.of(resourceCore));


        boolean result = resourceCoreService.deleteResourceCoreItem(resourceCore.getName(), resourceType, levelUpResource.getName());


        assertTrue(result);
        assertTrue(resourceCore.getLevelUpResources().isEmpty()); // levelUpResources가 비어있어야 한다.
        verify(resourceCoreRepository, times(1)).save(resourceCore); // 저장 메서드가 한 번 호출됐는지 확인
    }

    @Test//CoreskillResource 삭제 성공
    public void testDeleteCoreSkillResource_Success() {
        String resourceType = "coreSkill";
        resourceCore.getCoreSkillResources().add(coreSkillResource);


        when(resourceCoreRepository.findById(resourceCore.getName())).thenReturn(Optional.of(resourceCore));


        boolean result = resourceCoreService.deleteResourceCoreItem(resourceCore.getName(), resourceType, coreSkillResource.getName());


        assertTrue(result);
        assertTrue(resourceCore.getCoreSkillResources().isEmpty()); // coreSkillResources가 비어있어야 한다.
        verify(resourceCoreRepository, times(1)).save(resourceCore); // 저장 메서드가 한 번 호출됐는지 확인
    }

    @Test// SkillResource 삭제 성공
    public void testDeleteSkillResource_Success() {
        String resourceType = "skill";
        resourceCore.getSkillResources().add(skillResource);


        when(resourceCoreRepository.findById(resourceCore.getName())).thenReturn(Optional.of(resourceCore));


        boolean result = resourceCoreService.deleteResourceCoreItem(resourceCore.getName(), resourceType, skillResource.getName());


        assertTrue(result);
        assertTrue(resourceCore.getSkillResources().isEmpty()); // skillResources가 비어있어야 한다.
        verify(resourceCoreRepository, times(1)).save(resourceCore); // 저장 메서드가 한 번 호출됐는지 확인
    }

    @Test// BreakthroughResource 삭제 성공
    public void testDeleteBreakthroughResource_Success() {
        String resourceType = "breakthrough";
        resourceCore.getBreakthroughResources().add(breakthroughResource);


        when(resourceCoreRepository.findById(resourceCore.getName())).thenReturn(Optional.of(resourceCore));


        boolean result = resourceCoreService.deleteResourceCoreItem(resourceCore.getName(), resourceType, breakthroughResource.getName());


        assertTrue(result);
        assertTrue(resourceCore.getBreakthroughResources().isEmpty()); // breakthroughResources가 비어있어야 한다.
        verify(resourceCoreRepository, times(1)).save(resourceCore); // 저장 메서드가 한 번 호출됐는지 확인
    }

    @Test
    public void testDeleteResourceCoreItem_Fail_WhenResourceCoreNotFound() {
        // given: ResourceCore가 없는 경우
        when(resourceCoreRepository.findById("NonExistingResource")).thenReturn(Optional.empty());

        boolean result = resourceCoreService.deleteResourceCoreItem("NonExistingResource", "levelUp", "LevelUp1");


        assertFalse(result);
        verify(resourceCoreRepository, never()).save(any(ResourceCore.class));
    }

    @Test
    public void testDeleteResourceCoreItem_Fail_WhenResourceNotFound() {
        // given: ResourceCore는 있지만 삭제할 자원이 없는 경우
        when(resourceCoreRepository.findById("ExperienceResource")).thenReturn(Optional.of(resourceCore));

        boolean result = resourceCoreService.deleteResourceCoreItem("ExperienceResource", "levelUp", "NonExistingLevelUp");

        assertFalse(result);
        verify(resourceCoreRepository, never()).save(any(ResourceCore.class)); // save 메서드는 호출되지 않았어야 한다.
    }

    @Test
    public void testDeleteResourceCoreItem_Fail_WhenInvalidResourceType() {

        when(resourceCoreRepository.findById("ExperienceResource")).thenReturn(Optional.of(resourceCore));

        boolean result = resourceCoreService.deleteResourceCoreItem("ExperienceResource", "invalidType", "LevelUp1");

        assertFalse(result);
        verify(resourceCoreRepository, never()).save(any(ResourceCore.class)); // save 메서드는 호출되지 않았어야 한다.
    }
}
