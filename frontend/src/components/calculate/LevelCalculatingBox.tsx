import React from "react";
import "../../styles/calculatingBox.css";
import {useLevelUpCalculator} from "../../hooks/calculate/useLevelUpCalculator.tsx";
import {useBreakthroughCalculator} from "../../hooks/calculate/useBreakthroughCalculator.tsx";
import {useSkillUpgradeCalculator} from "../../hooks/calculate/useSkillLevelUpCalculator.tsx";
import {useCoreSkillUpgradeCalculator} from "../../hooks/calculate/useCoreSkillResourceCalculator.tsx";

export const LevelCalculatingBox: React.FC = () => {
    const { goalLevel, setGoalLevel, usedResources, calculateExpResources } = useLevelUpCalculator();
    const { usedBreakthroughs, calculateBreakthroughResources } = useBreakthroughCalculator(goalLevel);
    const { goalSkillLevel, setGoalSkillLevel, usedSkillResources, calculateSkillResources } = useSkillUpgradeCalculator();
    const { goalCoreSkillLevel, setGoalCoreSkillLevel, usedCoreSkillResources, calculateCoreSkillResources } = useCoreSkillUpgradeCalculator();

    const handleGoalLevelChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
        setGoalLevel(parseInt(e.target.value));
    };

    const handleGoalSkillLevelChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
        setGoalSkillLevel(parseInt(e.target.value));
    };

    const handleGoalCoreSkillLevelChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
        setGoalCoreSkillLevel(parseInt(e.target.value));
    };

    const handleCalculate = () => {
        calculateExpResources();
        calculateBreakthroughResources();
        calculateSkillResources()
        calculateCoreSkillResources();

    };

    return (
        <div className="calculating-box">
            <h2>목표레벨 계산</h2>


            <div className="goal-level">
                <label htmlFor="goal-level">목표 레벨</label>
                <select id="goal-level" value={goalLevel} onChange={handleGoalLevelChange}>
                    <option value={20}>20</option>
                    <option value={30}>30</option>
                    <option value={40}>40</option>
                    <option value={50}>50</option>
                    <option value={60}>60</option>
                </select>
                <label htmlFor="goal-level">레벨</label>
            </div>

            {/* 🔹 목표 스킬 레벨 선택 */}
            <div className="goal-skill-level">
                <label htmlFor="goal-skill-level">목표 스킬 레벨</label>
                <select id="goal-skill-level" value={goalSkillLevel} onChange={handleGoalSkillLevelChange}>
                    {[...Array(12)].map((_, i) => (
                        <option key={i + 1} value={i + 1}>{i + 1}</option>
                    ))}
                </select>
            </div>

            {/* 🔹 목표 CoreSkill 레벨 선택 */}
            <div className="goal-core-skill-level">
                <label htmlFor="goal-core-skill-level">목표 CoreSkill 레벨</label>
                <select id="goal-core-skill-level" value={goalCoreSkillLevel} onChange={handleGoalCoreSkillLevelChange}>
                    {[...Array(6)].map((_, i) => (
                        <option key={i + 1} value={i + 1}>{i + 1}</option>
                    ))}
                </select>
            </div>


            <div className="goal-resource">
                <h3>필요 총 재료 개수</h3>
                <ul>
                    {Object.entries(usedResources).map(([name, count]) => (
                        <li key={name}>{name}: {count}개</li>
                    ))}
                </ul>
            </div>

            <div className="breakthrough-resource">
                <h3>필요 돌파 재료</h3>
                <ul>
                    {Object.entries(usedBreakthroughs).map(([name, count]) => (
                        <li key={name}>{name}: {count}개</li>
                    ))}
                </ul>
            </div>


            <div className="skill-resource">
                <h3>필요 스킬 재료</h3>
                <ul>
                    {Object.entries(usedSkillResources).map(([name, count]) => (
                        <li key={name}>{name}: {count}개</li>
                    ))}
                </ul>
            </div>

            <div className="core-skill-resource">
                <h3>필요 핵심스킬 재료</h3>
                <ul>
                    {Object.entries(usedCoreSkillResources).map(([type, count]) => (
                        <li key={type}>{type}: {count}개</li>
                    ))}
                </ul>
            </div>

            <button onClick={handleCalculate}>계산하기</button>
        </div>
    );
};
