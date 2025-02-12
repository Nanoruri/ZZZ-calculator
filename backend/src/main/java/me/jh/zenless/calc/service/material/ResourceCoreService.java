package me.jh.zenless.calc.service.material;

import me.jh.zenless.calc.entity.material.ResourceCore;
import me.jh.zenless.calc.repository.material.ResourceCoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceCoreService {

    private final ResourceCoreRepository resourceCoreRepository;

    @Autowired
    public ResourceCoreService(ResourceCoreRepository resourceCoreRepository) {
        this.resourceCoreRepository = resourceCoreRepository;
    }


    // Create: ResourceCore와 자원 엔티티들을 생성
    public boolean createResourceCore(ResourceCore resourceCore) {

        ResourceCore newResourceCore = new ResourceCore();
        newResourceCore.setName(resourceCore.getName());
        newResourceCore.setLevelUpResources(resourceCore.getLevelUpResources());
        newResourceCore.setSkillResources(resourceCore.getSkillResources());
        newResourceCore.setCoreSkillResources(resourceCore.getCoreSkillResources());
        newResourceCore.setBreakthroughResources(resourceCore.getBreakthroughResources());

        resourceCoreRepository.save(newResourceCore);
        return true;
    }

    // Read: ResourceCore 및 그에 포함된 자원 엔티티들을 조회
    public ResourceCore getResourceCoreById(String resourceCoreId) {
        return resourceCoreRepository.findById(resourceCoreId).orElse(null);
    }

    // Read: 모든 ResourceCore와 그에 포함된 자원 엔티티들 조회
    public List<ResourceCore> getAllResourceCores() {
        return resourceCoreRepository.findAll();
    }

    // Update: ResourceCore 및 자원 엔티티들의 수정
    public ResourceCore updateResourceCore(String resourceCoreId, ResourceCore updatedResourceCore) {
        ResourceCore existingResourceCore = resourceCoreRepository.findById(resourceCoreId).orElse(null);
        if (existingResourceCore != null) {
            existingResourceCore.setName(updatedResourceCore.getName());
            existingResourceCore.setLevelUpResources(updatedResourceCore.getLevelUpResources());
            existingResourceCore.setSkillResources(updatedResourceCore.getSkillResources());
            existingResourceCore.setCoreSkillResources(updatedResourceCore.getCoreSkillResources());
            existingResourceCore.setBreakthroughResources(updatedResourceCore.getBreakthroughResources());
            return resourceCoreRepository.save(existingResourceCore);
        }
        return null;
    }


    // Delete: ResourceCore 및 그에 포함된 자원 엔티티들 삭제
    public boolean deleteResourceCoreItem(String resourceCoreId, String resourceType, String resourceId) {
        // ResourceCore 객체를 조회
        ResourceCore resourceCore = resourceCoreRepository.findById(resourceCoreId).orElse(null);

        if (resourceCore != null) {
            boolean isDeleted = false;

            // 삭제할 자원의 타입에 맞춰 삭제
            switch (resourceType) {
                case "levelUp":
                    isDeleted = resourceCore.getLevelUpResources().removeIf(resource -> resource.getName().equals(resourceId));
                    break;
                case "skill":
                    isDeleted = resourceCore.getSkillResources().removeIf(resource -> resource.getName().equals(resourceId));
                    break;
                case "coreSkill":
                    isDeleted = resourceCore.getCoreSkillResources().removeIf(resource -> resource.getName().equals(resourceId));
                    break;
                case "breakthrough":
                    isDeleted = resourceCore.getBreakthroughResources().removeIf(resource -> resource.getName().equals(resourceId));
                    break;
                default:
                    return false; // 자원 타입이 잘못된 경우
            }

            // 자원 삭제가 되었으면 ResourceCore 저장
            if (isDeleted) {
                resourceCoreRepository.save(resourceCore); // 변경된 ResourceCore 저장
                return true;
            }
        }
        return false; // ResourceCore이 존재하지 않거나 자원이 없을 경우
    }
}
