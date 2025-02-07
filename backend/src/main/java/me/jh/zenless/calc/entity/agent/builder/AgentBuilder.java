package me.jh.zenless.calc.entity.agent.builder;

import me.jh.zenless.calc.entity.RoleType;
import me.jh.zenless.calc.entity.agent.Agent;
import me.jh.zenless.calc.entity.agent.Skill;

import java.util.List;

public interface AgentBuilder {

    public interface Name {
        Role name(String name);
    }

    public interface Role {
        ElementType role(RoleType role);
    }

    public interface ElementType {
        Level elementType(me.jh.zenless.calc.entity.ElementType elementType);
    }

    public interface Level {
        Exp level(int level);
    }

    public interface Exp {
        CoreSkill exp(int exp);
    }

    public interface CoreSkill {
        Skills coreSkill(me.jh.zenless.calc.entity.agent.CoreSkill coreSkill);
    }

    public interface Skills {
        Build skills(List<Skill> skills);
    }

    public interface Build {
        Agent build();
    }

}
