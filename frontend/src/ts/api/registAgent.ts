export interface Skill {
    id: number;
    name: string;
    level: number;
    maxLevel: number;
}

export interface Agent {
    name: string;
    role: string; // 역할 정보
    elementType: string; // 속성 정보
    level: number;
    exp: number;
    coreSkill: { name: string; level: number; description: string };
    skills: Skill[];
}

// 캐릭터 등록 API
export const registerCharacter = async (agent: Agent): Promise<void> => {
    try {
        const response = await fetch("http://localhost:8085/zenless-calc/agent/api", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json",
            },
            body: JSON.stringify(agent),
        });

        if (!response.ok) {
            throw new Error("캐릭터 등록 실패");
        }

        console.log("✅ 캐릭터 등록 성공");
    } catch (error) {
        console.error("캐릭터 등록 API 오류:", error);
        throw error;
    }
};
