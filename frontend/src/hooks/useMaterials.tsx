import { useEffect, useState } from "react";
import { getMaterials} from "../ts/api/getMaterial.ts";
import {ResourceCore} from "../ts/api/registMaterial.ts";

export const useMaterials = () => {
    const [materials, setMaterials] = useState<ResourceCore[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const data = await getMaterials();
                setMaterials(data);
            } catch (error) {
                setError("재료 정보를 가져오는 데 문제가 발생했습니다.");
                console.error("데이터 로딩 실패:", error);
            } finally {
                setLoading(false);
            }
        };

        fetchData().catch((error) => {
            console.error("fetchData 함수 호출 중 에러 발생:", error);
        });
    }, []);

    return { materials, loading, error };
};