import React from "react";
import "../../styles/calculatingBox.css";
import {useLevelUpCalculator} from "../../hooks/calculate/useLevelUpCalculator.tsx";
import {useBreakthroughCalculator} from "../../hooks/calculate/useBreakthroughCalculator.tsx";
import {useSkillUpgradeCalculator} from "../../hooks/calculate/useSkillLevelUpCalculator.tsx";
import {useCoreSkillUpgradeCalculator} from "../../hooks/calculate/useCoreSkillResourceCalculator.tsx";
import {useLocation} from "react-router-dom";
import {Agent} from "../../ts/api/getCharacterInfo.ts";
import ResourceList from "../material/ResourceList.tsx";

export const LevelCalculatingBox: React.FC = () => {
    const { goalLevel, setGoalLevel, usedResources, calculateExpResources } = useLevelUpCalculator();
    const { usedBreakthroughs, calculateBreakthroughResources } = useBreakthroughCalculator(goalLevel);
    const { goalSkillLevel, setGoalSkillLevel, usedSkillResources, calculateSkillResources } = useSkillUpgradeCalculator();
    const { goalCoreSkillLevel, setGoalCoreSkillLevel, usedCoreSkillResources, calculateCoreSkillResources } = useCoreSkillUpgradeCalculator();


    const location = useLocation();
    const { character }: { character: Agent } = location.state || {}; // 전달된 캐릭터 데이터

    if (!character) return <p>캐릭터 정보가 없습니다.</p>;



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


            {character ? (
                <div className="character-info">
                    <h3>선택한 캐릭터</h3>
                    {/*<img src={character.role} alt={character.name} className="character-image" />*/}
                    <p>이름: {character.name}</p>
                    <p>역할: {character.role}</p>
                    <p>속성: {character.elementType}</p>
                </div>
            ) : (
                <p>캐릭터 정보를 찾을 수 없습니다.</p>
            )}


            <div className="goal-level">
                <label htmlFor="goal-level">목표 레벨</label>
                <select id="goal-level" value={goalLevel} onChange={handleGoalLevelChange}>
                    <option value={20}>20</option>
                    <option value={30}>30</option>
                    <option value={40}>40</option>
                    <option value={50}>50</option>
                    <option value={60}>60</option>
                </select>
            </div>

            {/* 🔹 목표 스킬 레벨 선택 */}
            <div className="goal-skill-level">
                <label htmlFor="goal-skill-level">스킬 레벨</label>
                <select id="goal-skill-level" value={goalSkillLevel} onChange={handleGoalSkillLevelChange}>
                    {[...Array(12)].map((_, i) => (
                        <option key={i + 1} value={i + 1}>{i + 1}</option>
                    ))}
                </select>
            </div>

            {/* 🔹 목표 CoreSkill 레벨 선택 */}
            <div className="goal-core-skill-level">
                <label htmlFor="goal-core-skill-level"> 핵심 스킬 레벨</label>
                <select id="goal-core-skill-level" value={goalCoreSkillLevel} onChange={handleGoalCoreSkillLevelChange}>
                    {[...Array(6)].map((_, i) => (
                        <option key={i + 1} value={i + 1}>{i + 1}</option>
                    ))}
                </select>
            </div>


            <ResourceList title="필요 총 재료 개수" resources={usedResources} />
            <ResourceList title="필요 돌파 재료" resources={usedBreakthroughs} />
            <ResourceList title="필요 스킬 재료" resources={usedSkillResources} />
            <ResourceList title="필요 핵심 스킬 재료" resources={usedCoreSkillResources} />

            <button className="calculate-button" onClick={handleCalculate}>계산하기</button>
        </div>
    );
};
