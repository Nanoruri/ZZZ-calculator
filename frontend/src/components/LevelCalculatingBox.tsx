import React, {useState} from "react";
import "../styles/calculatingBox.css";
import {useMaterials} from "../hooks/useMaterials.tsx";

// 이 컴포넌트는 캐릭터의 육성 목표치를 설정하여 목표치에 대한 자원을 보여주는 컴포넌트입니다.

// 목표레벨을 설정(셀렉박스)
// 목표레벨에 따른 자원을 계산하여 보여줌 (계산하기 버튼)(계산 컴포넌트는 아직 미구현)
//ResourceCore.BreakthroughResource에서 레벨 범위를 가져와서 계산해야함

// 목표치에 대한 자원을 보여줌

export const LevelCalculatingBox: React.FC = () => {
    const { materials } = useMaterials();
    const [goalLevel, setGoalLevel] = useState<number>(20);
    const [goalResource, setGoalResource] = useState<number>(0);
    const [usedResources, setUsedResources] = useState<Record<string, number>>({});
    const [usedBreakthroughs, setUsedBreakthroughs] = useState<Record<string, number>>({});

    // 목표 레벨별 필요 경험치
    const levelExpRequirements: Record<number, number> = {
        20: 5000,
        30: 15000,
        40: 50000,
        60: 120000,
    };

    // 레벨업 재료와 돌파 재료 가져오기
    const levelUpResources = materials.flatMap(material => material.levelUpResources || []);
    const breakthroughResources = materials.flatMap(material => material.breakthroughResources || []);

    // 레벨업 재료: 경험치 높은 순서대로 정렬
    const sortedResources = [...levelUpResources].sort((a, b) => b.experience - a.experience);

    const handleGoalLevelChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
        setGoalLevel(parseInt(e.target.value));
    };

    const handleCalculate = () => {
        let remainingExp = levelExpRequirements[goalLevel] || 0;
        let totalResources = 0;
        let resourceUsage: Record<string, number> = {};

        // 🟢 경험치 높은 순서대로 아이템 사용
        for (const resource of sortedResources) {
            const { name, experience } = resource;
            const maxUsable = Math.floor(remainingExp / experience);

            if (maxUsable > 0) {
                resourceUsage[name] = maxUsable;
            }

            totalResources += maxUsable;
            remainingExp -= maxUsable * experience;

            if (remainingExp <= 0) break;
        }

        setGoalResource(totalResources);
        setUsedResources(resourceUsage);

        // 🟢 돌파 재료 계산 (DB 데이터 활용)
        let breakthroughUsage: Record<string, number> = {};

        breakthroughResources.forEach(resource => {
            const { name, levelRangeStart, levelRangeEnd } = resource; // 초급부터 시작
            if (goalLevel > levelRangeStart) {
                if (levelRangeEnd === 20) {
                    breakthroughUsage[name] = (breakthroughUsage[name] || 0) + 4;
                } else if (levelRangeEnd === 40) {
                    breakthroughUsage[name] = (breakthroughUsage[name] || 0) + (goalLevel <= 30 ? 12 : 32);
                } else if (levelRangeEnd === 60) {
                    breakthroughUsage[name] = (breakthroughUsage[name] || 0) + (goalLevel <= 50 ? 10 : 30);
                }
            }
        });

        setUsedBreakthroughs(breakthroughUsage);
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

