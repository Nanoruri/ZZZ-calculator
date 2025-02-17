import {useMaterials} from "../useMaterials.tsx";
import {useState} from "react";

export const useCoreSkillUpgradeCalculator = () => {
    const { materials } = useMaterials();

    // 목표 CoreSkill 레벨 상태 (기본값: 1)
    const [goalCoreSkillLevel, setGoalCoreSkillLevel] = useState(1);

    // 사용된 CoreSkill 재료 상태
    const [usedCoreSkillResources, setUsedCoreSkillResources] = useState<Record<string, number>>({});

    // CoreSkill 업그레이드 재료 필터링
    const coreSkillResources = materials.flatMap(material => material.coreSkillResources || []);

    // 단계별 필요 재료
    const levelRequirements = [
        { start: 1, end: 2, highData: 2, etherCondensate: 0 },
        { start: 2, end: 3, highData: 4, etherCondensate: 0 },
        { start: 3, end: 4, highData: 9, etherCondensate: 2 },
        { start: 4, end: 5, highData: 11, etherCondensate: 3 },
        { start: 5, end: 6, highData: 30, etherCondensate: 4 }
    ];

    // 필요 CoreSkill 재료 계산
    const calculateCoreSkillResources = () => {
        if (goalCoreSkillLevel <= 1) return;

        const requiredResources: Record<string, number> = {};

        for (const { start, end, highData, etherCondensate } of levelRequirements) {
            if (goalCoreSkillLevel > start) {
                // 고차원 데이터 필터링
                const highDataResource = coreSkillResources.find(res => res.type === "고차원 데이터");
                if (highDataResource) {
                    requiredResources[highDataResource.name] = (requiredResources[highDataResource.name] || 0) + highData;
                }

                // 에테르 응집물 필터링
                const etherCondensateResource = coreSkillResources.find(res => res.type === "에테르 응집물");
                if (etherCondensateResource) {
                    requiredResources[etherCondensateResource.name] = (requiredResources[etherCondensateResource.name] || 0) + etherCondensate;
                }
            }
        }

        setUsedCoreSkillResources(requiredResources);
    };

    return {
        goalCoreSkillLevel,
        setGoalCoreSkillLevel,
        usedCoreSkillResources,
        calculateCoreSkillResources
    };
};
