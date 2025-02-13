import React, { useState, useEffect } from "react";
import { ResourceCore, registerMaterial as apiRegisterMaterial } from "../ts/api/registMaterial";

export const useRegisterMaterial = () => {
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);
    const [resourceType, setResourceType] = useState<string>("CoreSkillResource");

    const [resourceCore, setResourceCore] = useState<ResourceCore>({
        name: "",
        coreSkillResources: [],
        levelUpResources: [],
        skillResources: [],
        breakthroughResources: [],
    });

    const [newResource, setNewResource] = useState({
        name: "",
        type: "",
        experience: 0,
        attribute: "",
        levelRangeStart: 0,
        levelRangeEnd: 0
    });

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setNewResource((prev) => ({
            ...prev,
            [name]: value,
        }));
    };

    // 자원 추가 핸들러
    const addResource = () => {
        setResourceCore((prev) => {
            const updatedCore = { ...prev };
            const newResourceEntry = { ...newResource };

            switch (resourceType) {
                case "CoreSkillResource":
                    updatedCore.coreSkillResources = [...updatedCore.coreSkillResources, newResourceEntry];
                    break;
                case "LevelUpResource":
                    updatedCore.levelUpResources = [...updatedCore.levelUpResources, newResourceEntry];
                    break;
                case "SkillResource":
                    updatedCore.skillResources = [...updatedCore.skillResources, newResourceEntry];
                    break;
                case "BreakthroughResource":
                    updatedCore.breakthroughResources = [...updatedCore.breakthroughResources, newResourceEntry];
                    break;
                default:
                    break;
            }
            return updatedCore;
        });

        // 입력 필드 초기화
        setNewResource({
            name: "",
            type: "",
            experience: 0,
            attribute: "",
            levelRangeStart: 0,
            levelRangeEnd: 0
        });
    };

    // 자원 등록 API 호출
    const registerMaterial = async () => {
        setLoading(true);
        setError(null);
        try {
            await apiRegisterMaterial(resourceCore);
            console.log("✅ 재료 등록 성공");
        } catch (error) {
            console.error("재료 등록 API 오류:", error);
            setError("재료 등록 실패");
        } finally {
            setLoading(false);
        }
    };

    // addResource 후에 바로 registerMaterial을 호출하기 위한 useEffect
    useEffect(() => {
        if (resourceCore.coreSkillResources.length > 0 || resourceCore.levelUpResources.length > 0 ||
            resourceCore.skillResources.length > 0 || resourceCore.breakthroughResources.length > 0) {
            registerMaterial().then(() => {
                console.log("등록 완료!");
            });
        }
    }, [resourceCore]);  // resourceCore 상태가 변경될 때마다 호출됨

    const addResourceAndRegister = async () => {
        addResource();
    };

    return {
        loading,
        error,
        resourceType,
        resourceCore,
        newResource,
        handleChange,
        addResourceAndRegister,
        setResourceType,
    };
};
