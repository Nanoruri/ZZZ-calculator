package me.jh.zenless.calc.entity.agent.builder;

import me.jh.zenless.calc.entity.ElementType;
import me.jh.zenless.calc.entity.RoleType;
import me.jh.zenless.calc.entity.agent.Agent;
import me.jh.zenless.calc.entity.agent.CoreSkill;
import me.jh.zenless.calc.entity.agent.Skill;

import java.util.List;

public class AgentStep implements AgentBuilder.Name, AgentBuilder.Role, AgentBuilder.ElementType, AgentBuilder.Level, AgentBuilder.Exp, AgentBuilder.CoreSkill, AgentBuilder.Skills, AgentBuilder.Build {

    private String name;
    private RoleType role;
    private ElementType elementType;
    private int level;
    private int exp;
    private CoreSkill coreSkill;
    private List<Skill> skills;

    private AgentStep() {
    }

    public static AgentBuilder.Name builder() {
        return new AgentStep();
    }

    @Override
    public AgentBuilder.Role name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public AgentBuilder.ElementType role(RoleType role) {
        this.role = role;
        return this;
    }

    @Override
    public AgentBuilder.Level elementType(ElementType elementType) {
        this.elementType = elementType;
        return this;
    }

    @Override
    public AgentBuilder.Exp level(int level) {
        this.level = level;
        return this;
    }

    @Override
    public AgentBuilder.CoreSkill exp(int exp) {
        this.exp = exp;
        return this;
    }

    @Override
    public AgentBuilder.Skills coreSkill(CoreSkill coreSkill) {
        this.coreSkill = coreSkill;
        return this;
    }

    @Override
    public AgentBuilder.Build skills(List<Skill> skills) {
        this.skills = skills;
        return this;
    }

    @Override
    public Agent build() {
        return new Agent(name, role, elementType, level, exp, coreSkill, skills);
    }
}
