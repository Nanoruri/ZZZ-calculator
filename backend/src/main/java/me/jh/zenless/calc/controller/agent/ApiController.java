package me.jh.zenless.calc.controller.agent;

import me.jh.zenless.calc.entity.agent.Agent;
import me.jh.zenless.calc.service.agent.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agent/api")
public class ApiController {


    private final AgentService agentService;

    @Autowired
    public ApiController(AgentService agentService) {
        this.agentService = agentService;
    }


    @PostMapping
    public ResponseEntity<Agent> createAgent(@RequestBody Agent agent) {
        if (!agentService.createAgent(agent)) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(agent);
    }


    @GetMapping("{name}")
    public ResponseEntity<Agent> getAgent(@PathVariable String name) {
        if (name == null || name.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Agent agent = agentService.getAgent(name);

        return ResponseEntity.ok(agent);
    }

    @GetMapping
    public ResponseEntity<List<Agent>> getAgents() {
        List<Agent> agentList = agentService.getAgents();

        return ResponseEntity.ok(agentList);
    }

    @PutMapping
    public ResponseEntity<Agent> updateAgent(@RequestBody Agent agent) {
        if (!agentService.updateAgent(agent)) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(agent);
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Agent> deleteAgent(@PathVariable String name) {
        if (name == null || name.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Agent agent = agentService.deleteAgent(name);

        return ResponseEntity.ok(agent);
    }
}
