package me.jh.zenless.calc.controller.material;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.jh.zenless.calc.entity.ElementType;
import me.jh.zenless.calc.entity.material.*;
import me.jh.zenless.calc.service.material.ResourceCoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(ResourceApiController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ResourceApiControllerTest {


    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @MockBean
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
    public void setUp() throws Exception {
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
    void createResource_shouldReturnOk_whenResourceIsValid() throws Exception {
        resourceCore.setBreakthroughResources(Set.of(breakthroughResource));


        when(resourceCoreService.createResourceCore(any(ResourceCore.class))).thenReturn(true);

        mockMvc.perform(post("/material/api")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resourceCore)))
                .andExpect(status().isOk());
    }

    @Test
    void createResource_shouldReturnBadRequest_whenResourceIsInvalid() throws Exception {
        resourceCore.setBreakthroughResources(Set.of(breakthroughResource));

        when(resourceCoreService.createResourceCore(any(ResourceCore.class))).thenReturn(false);

        mockMvc.perform(post("/material/api")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resourceCore)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getAllResources_shouldReturnOk_withListOfResources() throws Exception {
        resourceCore.setSkillResources(Set.of(skillResource));
        resourceCore.setLevelUpResources(Set.of(levelUpResource));
        resourceCore.setCoreSkillResources(Set.of(coreSkillResource));
        resourceCore.setBreakthroughResources(Set.of(breakthroughResource));

        List<ResourceCore> resources = List.of(resourceCore);

        when(resourceCoreService.getAllResourceCores()).thenReturn(resources);

        mockMvc.perform(get("/material/api"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(resources.size()));
    }

    @Test
    void getResourceById_shouldReturnOk_withResource() throws Exception {
        resourceCore.setSkillResources(Set.of(skillResource));


        when(resourceCoreService.getResourceCoreById("경험치 재료")).thenReturn(resourceCore);

        mockMvc.perform(get("/material/api/경험치 재료"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("경험치 재료"));
    }

    @Test
    void updateResource_shouldReturnOk_whenResourceIsUpdated() throws Exception {
        resourceCore.setSkillResources(Set.of(skillResource));

        when(resourceCoreService.updateResourceCore(anyString(), any(ResourceCore.class))).thenReturn(resourceCore);

        mockMvc.perform(put("/material/api/경험치 재료")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resourceCore)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("경험치 재료"));
    }

    @Test
    void updateResource_shouldReturnBadRequest_whenResourceIsNotUpdated() throws Exception {

        when(resourceCoreService.updateResourceCore(anyString(), any(ResourceCore.class))).thenReturn(null);

        mockMvc.perform(put("/material/api/type")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(resourceCore)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteResource_shouldReturnOk_whenResourceIsDeleted() throws Exception {
        when(resourceCoreService.deleteResourceCoreItem(anyString(), anyString(), anyString())).thenReturn(true);

        mockMvc.perform(delete("/material/api/type/coreId/id"))
                .andExpect(status().isOk());

    }

    @Test
    void deleteResource_shouldReturnBadRequest_whenResourceIsNotDeleted() throws Exception {
        when(resourceCoreService.deleteResourceCoreItem(anyString(), anyString(), anyString())).thenReturn(false);

        mockMvc.perform(delete("/material/api/type/coreId/id"))
                .andExpect(status().isBadRequest());
    }
}