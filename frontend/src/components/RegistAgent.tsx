import React from "react";
import { useCharacterForm } from "../hooks/useCharacterForm";
import SkillInput from "../components/SkillInput";
import "../styles/registAgent.css";

const roleTypes = {
    STRONG: "강공",
    BREAK: "격파",
    ABNORMAL: "이상",
    SUPPORT: "지원",
    DEFENSE: "방어"
};

const elementTypes = {
    PHYSICAL: "물리",
    FIRE: "불",
    ICE: "얼음",
    ELECTRIC: "전기",
    ETHER: "에테르"
};

const CharacterRegister: React.FC = () => {
    const { formData, message, handleChange, handleCoreSkillChange, handleSkillChange, addSkill, removeSkill, handleSubmit } = useCharacterForm();

    return (
        <div className="character-register">
            <h2>캐릭터 등록</h2>
            {message && <p>{message}</p>}
            <form onSubmit={handleSubmit}>
                <label>
                    이름:
                    <input type="text" name="name" value={formData.name} onChange={handleChange} required/>
                </label>
                <label>
                    역할:
                    <select name="role" value={formData.role} onChange={handleChange} required>
                        <option value="">역할 선택</option>
                        {Object.entries(roleTypes).map(([key, value]) => (
                            <option key={key} value={key}>{value}</option>
                        ))}
                    </select>
                </label>
                <label>
                    속성:
                    <select name="elementType" value={formData.elementType} onChange={handleChange} required>
                        <option value="">속성 선택</option>
                        {Object.entries(elementTypes).map(([key, value]) => (
                            <option key={key} value={key}>{value}</option>
                        ))}
                    </select>
                </label>
                <label>
                    레벨:
                    <input type="number" name="level" value={formData.level} onChange={handleChange} required min="1" max="60"/>
                </label>
                <label>
                    경험치:
                    <input type="number" name="exp" value={formData.exp} onChange={handleChange} required min="0"/>
                </label>
                <label>
                    핵심 스킬 이름:
                    <input type="text" name="name" value={formData.coreSkill.name} onChange={handleCoreSkillChange} required/>
                </label>
                <h3>스킬 목록 (최대 5개)</h3>
                {formData.skills.map((skill, index) => (
                    <SkillInput key={skill.id} skill={skill} index={index} handleSkillChange={handleSkillChange} removeSkill={removeSkill} />
                ))}

                {formData.skills.length < 5 && (
                    <button type="button" onClick={addSkill}>+ 스킬 추가</button>
                )}

                <button type="submit">캐릭터 등록</button>
            </form>
        </div>
    );
};

export default CharacterRegister;