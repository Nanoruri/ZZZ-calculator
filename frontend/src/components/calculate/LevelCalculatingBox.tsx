import React from "react";
import "../../styles/calculatingBox.css";
import {useLevelUpCalculator} from "../../hooks/calculate/useLevelUpCalculator.tsx";
import {useBreakthroughCalculator} from "../../hooks/calculate/useBreakthroughCalculator.tsx";
import {useSkillUpgradeCalculator} from "../../hooks/calculate/useSkillLevelUpCalculator.tsx";
import {useCoreSkillUpgradeCalculator} from "../../hooks/calculate/useCoreSkillResourceCalculator.tsx";
import {useLocation} from "react-router-dom";
import {Agent} from "../../ts/api/getCharacterInfo.ts";
import ResourceList from "../material/ResourceList.tsx";
import GoalSelect from "./GoalSelect.tsx";

export const LevelCalculatingBox: React.FC = () => {
    const { goalLevel, setGoalLevel, usedResources, calculateExpResources } = useLevelUpCalculator();
    const { usedBreakthroughs, calculateBreakthroughResources } = useBreakthroughCalculator(goalLevel);
    const { goalSkillLevel, setGoalSkillLevel, usedSkillResources, calculateSkillResources } = useSkillUpgradeCalculator();
    const { goalCoreSkillLevel, setGoalCoreSkillLevel, usedCoreSkillResources, calculateCoreSkillResources } = useCoreSkillUpgradeCalculator();


    const location = useLocation();
    const { character }: { character: Agent } = location.state || {}; // 전달된 캐릭터 데이터

    if (!character) return <p>캐릭터 정보가 없습니다.</p>;


    const handleGoalLevelChange = (setter: (value: number) => void) => (e: React.ChangeEvent<HTMLSelectElement>) => {
        setter(parseInt(e.target.value));
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

            <GoalSelect
                label="목표 레벨"
                id="goal-level"
                value={goalLevel}
                onChange={handleGoalLevelChange(setGoalLevel)}
                options={[20, 30, 40, 50, 60]}
            />

            <GoalSelect
                label="스킬 레벨"
                id="goal-skill-level"
                value={goalSkillLevel}
                onChange={handleGoalLevelChange(setGoalSkillLevel)}
                options={[...Array(12)].map((_, i) => i + 1)}
            />

            <GoalSelect
                label="핵심 스킬 레벨"
                id="goal-core-skill-level"
                value={goalCoreSkillLevel}
                onChange={handleGoalLevelChange(setGoalCoreSkillLevel)}
                options={[...Array(6)].map((_, i) => i + 1)}
            />

            <ResourceList title="필요 총 재료 개수" resources={usedResources}/>
            <ResourceList title="필요 돌파 재료" resources={usedBreakthroughs}/>
            <ResourceList title="필요 스킬 재료" resources={usedSkillResources}/>
            <ResourceList title="필요 핵심 스킬 재료" resources={usedCoreSkillResources}/>

            <button className="calculate-button" onClick={handleCalculate}>계산하기</button>
        </div>
    );
};
