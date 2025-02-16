import { useState } from "react";
import {useMaterials} from "../useMaterials.tsx";


export const useBreakthroughCalculator = (goalLevel: number) => {
    const { materials } = useMaterials();
    const [usedBreakthroughs, setUsedBreakthroughs] = useState<Record<string, number>>({});

    const breakthroughResources = materials.flatMap(material => material.breakthroughResources || []);// 돌파 재료 배열

    const calculateBreakthroughResources = () => {
        const breakthroughUsage: Record<string, number> = {};

        breakthroughResources.forEach(resource => {
            const { name, levelRangeStart, levelRangeEnd } = resource;

            if (goalLevel > levelRangeStart) {
                let requiredAmount = 0;

                if (levelRangeStart === 1 && levelRangeEnd === 20) {
                    requiredAmount = 4;
                } else if (levelRangeStart === 20 && levelRangeEnd === 40) {
                    requiredAmount = 12 + (goalLevel > 30 ? 20 : 0);
                } else if (levelRangeStart === 40 && levelRangeEnd === 60) {
                    requiredAmount = 10 + (goalLevel > 50 ? 20 : 0);
                }

                if (requiredAmount > 0) {
                    breakthroughUsage[name] = (breakthroughUsage[name] || 0) + requiredAmount;
                }
            }
        });

        setUsedBreakthroughs(breakthroughUsage);
    };

    return { usedBreakthroughs, calculateBreakthroughResources };
};
