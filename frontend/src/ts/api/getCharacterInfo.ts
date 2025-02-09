

// 코어 스킬 타입
interface CoreSkill {
    name: string;
    level: number;
    description: string;
}

// 일반 스킬 타입
interface Skill {
    id: number;
    name: string;
    level: number;
    maxLevel: number;
}

// 에이전트 타입
export interface Agent {
    name: string;
    role: string;
    elementType: string;
    level: number;
    exp: number;
    coreSkill: CoreSkill;
    skills: Skill[];
}
// 기대 응답: Character[] (캐릭터 목록)
// 리스트로 캐릭터를 받아서 반환
export const fetchCharacters = async (): Promise<Agent[]> => {
    try {
        const response = await fetch("http://localhost:8085/zenless-calc/agent/api", {
            method: "GET",
            headers: {
                "Content-Type": "application/json", // JSON 요청을 명시
                "Accept": "application/json" // JSON 응답을 기대
            }
        });

        if (!response.ok) {
            throw new Error("캐릭터 데이터를 가져오는 데 실패했습니다."); //todo: fuck no.1
        }

        return await response.json(); // JSON 형태로 반환
    } catch (error) {
        console.error("API 호출 실패:", error);
        throw error; // 오류를 다시 던져서 호출하는 곳에서 처리하게 함
    }
};