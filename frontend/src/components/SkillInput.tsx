import React from "react";
import {Skill} from "../ts/api/registAgent";
import "../styles/skillInput.css";

interface SkillInputProps {
    skill: Skill;
    index: number;
    handleSkillChange: (index: number, field: string, value: string | number) => void;
    removeSkill: (index: number) => void;
}

const SkillInput: React.FC<SkillInputProps> = ({skill, index, handleSkillChange, removeSkill}) => {
    return (
        <div className="skill-entry">
            <input type="text" placeholder="스킬 이름" value={skill.name}
                   onChange={(e) => handleSkillChange(index, "name", e.target.value)} required/>
            <input type="number" placeholder="레벨" value={skill.level}
                   onChange={(e) => handleSkillChange(index, "level", Number(e.target.value))} required min="1"
                   max="12"/>
            <input type="number" placeholder="최대 레벨" value={skill.maxLevel}
                   onChange={(e) => handleSkillChange(index, "maxLevel", Number(e.target.value))} required min="1"
                   max="12"/>
            <button type="button" onClick={() => removeSkill(index)}>X</button>
        </div>
    );
};

export default SkillInput;