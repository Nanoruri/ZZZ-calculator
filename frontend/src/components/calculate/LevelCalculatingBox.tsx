import React from "react";
import "../../styles/calculatingBox.css";
import {useLevelUpCalculator} from "../../hooks/calculate/useLevelUpCalculator.tsx";
import {useBreakthroughCalculator} from "../../hooks/calculate/useBreakthroughCalculator.tsx";
import {useMaterials} from "../../hooks/useMaterials.tsx";

export const LevelCalculatingBox: React.FC = () => {
    const { goalLevel, setGoalLevel, usedResources, calculateExpResources } = useLevelUpCalculator();
    const { usedBreakthroughs, calculateBreakthroughResources } = useBreakthroughCalculator(goalLevel);


    const handleGoalLevelChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
        setGoalLevel(parseInt(e.target.value));
    };

    const handleCalculate = () => {
        calculateExpResources();
        calculateBreakthroughResources();
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

            <button onClick={handleCalculate}>계산하기</button>
        </div>
    );
};
