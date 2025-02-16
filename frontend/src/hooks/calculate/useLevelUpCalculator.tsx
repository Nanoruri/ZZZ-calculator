import { useState, useMemo } from "react";
import { useMaterials } from "../useMaterials";

export const useLevelUpCalculator = () => {
    const { materials } = useMaterials();
    const [goalLevel, setGoalLevel] = useState<number>(20);
    const [usedResources, setUsedResources] = useState<Record<string, number>>({});

    const levelExpRequirements: Record<number, number> = {// 목표레벨에 따른 필요 경험치
        20: 3000,
        30: 90000,
        40: 225000,
        50: 450000,
        60: 900000,
    };

    const levelUpResources = materials.flatMap(material => material.levelUpResources || []);
    const sortedResources = [...levelUpResources].sort((a, b) => b.experience - a.experience);


    const calculateExpResources = () => {
        let remainingExp = levelExpRequirements[goalLevel] || 0; // 경험치 초기화
        const resourceUsage: Record<string, number> = {};// 재료 사용량 초기화

        sortedResources.forEach(resource => {// 경험치가 높은 순으로 재료 사용량 계산
            const { name, experience } = resource;// levelUp Resource배열 내 재료 이름 및 경험치
            const maxUsable = Math.floor(remainingExp / experience);// 사용 가능한 최대 재료 개수

            if (maxUsable > 0) { // 사용 가능한 재료가 있을 경우
                resourceUsage[name] = maxUsable; // 사용한 재료 개수 기록
                remainingExp -= maxUsable * experience; // 사용한 만큼 경험치 차감
            }

            if (remainingExp <= 0) return; // 목표 경험치를 다 채웠을 경우 종료
        });

        setUsedResources(resourceUsage);
    };

    return { goalLevel, setGoalLevel, usedResources, calculateExpResources };
};
