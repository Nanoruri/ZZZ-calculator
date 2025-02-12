package me.jh.zenless.calc.controller.material;


import me.jh.zenless.calc.entity.material.ResourceCore;
import me.jh.zenless.calc.service.material.ResourceCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/material/api")
public class ResourceApiController {

    private final ResourceCoreService resourceCoreService;

    @Autowired
    public ResourceApiController(ResourceCoreService resourceCoreService) {
        this.resourceCoreService = resourceCoreService;
    }


    //CRUD

    //create
    @PostMapping
    public ResponseEntity<Boolean> createResource( @RequestBody ResourceCore resourceCore) {
        if (!resourceCoreService.createResourceCore(resourceCore)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(true);
    }

    @GetMapping
    public ResponseEntity<List<ResourceCore>> getAllResources() {
        List<ResourceCore> resourceCores = resourceCoreService.getAllResourceCores();
        return ResponseEntity.ok(resourceCores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourceCore> getResourceById(@PathVariable String id) {
        return ResponseEntity.ok(resourceCoreService.getResourceCoreById(id));
    }

    @PutMapping("/{type}")
    public ResponseEntity<ResourceCore> updateResource(@PathVariable String type, @RequestBody ResourceCore resource) {
        if (resourceCoreService.updateResourceCore(type, resource) == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("/{type}/{coreId}/{id}")
    public ResponseEntity<Boolean> deleteResource(@PathVariable String coreId, @PathVariable String type, @PathVariable String id) {
        if (!resourceCoreService.deleteResourceCoreItem(coreId, type, id)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(true);
    }

}
