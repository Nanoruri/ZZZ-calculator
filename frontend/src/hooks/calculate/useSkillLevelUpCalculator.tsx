import { useState } from "react";
import { useMaterials } from "../useMaterials";

export const useSkillUpgradeCalculator = () => {
    const { materials } = useMaterials();

    // 🔹 목표 스킬 레벨 상태
    const [goalSkillLevel, setGoalSkillLevel] = useState(1);

    // 🔹 계산된 스킬 재료 상태
    const [usedSkillResources, setUsedSkillResources] = useState<Record<string, number>>({});

    // 🔹 skillResources 데이터 추출
    const skillResources = materials.flatMap(material => material.skillResources || []);


    // 🔹 필요 재료 계산 함수
    const calculateSkillResources = () => {
        if (goalSkillLevel <= 1) return;

        const requiredResources: Record<string, number> = {};
        const levelRequirements = [
            { start: 1, end: 2, count: 2 },
            { start: 2, end: 3, count: 3 },
            { start: 3, end: 4, count: 2 },
            { start: 4, end: 5, count: 3 },
            { start: 5, end: 6, count: 4 },
            { start: 6, end: 7, count: 6 },
            { start: 7, end: 8, count: 5 },
            { start: 8, end: 9, count: 8 },
            { start: 9, end: 10, count: 10 },
            { start: 10, end: 11, count: 12 },
            { start: 11, end: 12, count: 15 },
        ];

        for (const { start, end, count } of levelRequirements) {
            if (goalSkillLevel > start) {
                const resource = skillResources.find(
                    (res) => res.levelRangeStart <= start && res.levelRangeEnd >= end
                );

                if (resource) {
                    requiredResources[resource.name] = (requiredResources[resource.name] || 0) + count;
                }
            }
        }

        setUsedSkillResources(requiredResources);
    };

    return {
        goalSkillLevel,
        setGoalSkillLevel,
        usedSkillResources,
        calculateSkillResources
    };
};
