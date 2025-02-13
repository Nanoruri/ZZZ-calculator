//http://localhost:8085/zenless-calc//material/api로 get 요청을 하여 재료를 가져오는 API 작성
import {ResourceCore} from "./registMaterial.ts";

export const getMaterials = async (): Promise<ResourceCore[]> => {
    try {
        const response = await fetch("http://localhost:8085/zenless-calc/material/api/", {
            method: "GET",
            headers: {
                "Content-Type": "application/json", // JSON 요청을 명시
                "Accept": "application/json" // JSON 응답을 기대
            }
        });

        if (!response.ok) {
            throw new Error("재료 정보를 가져오는데 실패했습니다."); //todo: fuck no.1
        }

        return await response.json(); // JSON 형태로 반환
    } catch (error) {
        console.error("API 호출 실패:", error);
        throw error; // 오류를 다시 던져서 호출하는 곳에서 처리하게 함
    }
};