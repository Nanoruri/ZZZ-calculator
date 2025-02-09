import { useEffect, useState } from "react";
import { fetchCharacters, Agent } from "../ts/api/getCharacterInfo.ts";

export const useCharacters = () => {
    const [characters, setCharacters] = useState<Agent[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const getData = async () => {
            try {
                const data = await fetchCharacters();
                setCharacters(data); // 데이터 상태에 저장
            } catch (error) {
                setError("캐릭터 데이터를 가져오는 데 문제가 발생했습니다.");
                console.error("데이터 로딩 실패:", error);
            } finally {
                setLoading(false);
            }
        };

        getData().catch((error) => {
            console.error("getData 함수 호출 중 에러 발생:", error);
        }); // 비동기 함수 호출 및 에러 처리
    }, []); // 컴포넌트 마운트 시 한 번만 실행

    return { characters, loading, error };
};