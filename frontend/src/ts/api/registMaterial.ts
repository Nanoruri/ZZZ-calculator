//http://localhost:8085/zenless-calc/material/api로 요청을 보내는 API를 작성
export interface CoreSkillResource {
    name: string;
    type: string;
}

export interface LevelUpResource {
    name: string;
    experience: number;
    type: string;
}

export interface SkillResource {
    name: string;
    type: string;
    levelRangeStart: number;
    levelRangeEnd: number;
}

export interface BreakthroughResource {
    name: string;
    attribute: string;
    type: string;
    levelRangeStart: number;
    levelRangeEnd: number;
}

export interface ResourceCore {
    name: string;
    coreSkillResources: CoreSkillResource[];
    levelUpResources: LevelUpResource[];
    skillResources: SkillResource[];
    breakthroughResources: BreakthroughResource[];
}

// 재료 등록 API
export const registerMaterial = async (resourceCore: ResourceCore): Promise<void> => {
    try {
        const response = await fetch("http://localhost:8085/zenless-calc/material/api", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json",
            },
            body: JSON.stringify(resourceCore),
        });

        if (!response.ok) {
            throw new Error("재료 등록 실패");
        }

        console.log("✅ 재료 등록 성공");
    } catch (error) {
        console.error("재료 등록 API 오류:", error);
        throw error;
    }
};
