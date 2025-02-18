import { renderHook, act } from "@testing-library/react";
import {useCoreSkillUpgradeCalculator} from "../../src/hooks/calculate/useCoreSkillResourceCalculator";




// Mock 데이터 설정
const mockMaterials = [
    {
        coreSkillResources: [
            { name: "형별 통지", type: "고차원 데이터" },
            { name: "피바람의 손아귀", type: "에테르 응집물" },
        ],
    },
];

jest.mock("../../src/hooks/useMaterials", () => ({
    useMaterials: () => ({
        materials: mockMaterials,
    }),
}));

describe("useCoreSkillResourceCalculatorTest", () => {
    it("testInitialState", () => {
        const { result } = renderHook(() => useCoreSkillUpgradeCalculator());

        expect(result.current.goalCoreSkillLevel).toBe(1);// 목표 레벨 1
        expect(result.current.usedCoreSkillResources).toEqual({});
    });

    it("testGoalCoreSkillLevelUpdate", () => {
        const { result } = renderHook(() => useCoreSkillUpgradeCalculator());

        act(() => {
            result.current.setGoalCoreSkillLevel(4);
        });

        expect(result.current.goalCoreSkillLevel).toBe(4);
    });

    //calculateCoreSkillResources를 호출하면 올바른 재료 개수가 계산되어야 한다
    it("testCoreSkillResourcesCalculation", () => {
        const { result } = renderHook(() => useCoreSkillUpgradeCalculator());

        act(() => {
            result.current.setGoalCoreSkillLevel(5);
        });

        act(() => {
            result.current.calculateCoreSkillResources();
        });

        expect(result.current.usedCoreSkillResources).toEqual({
            "형별 통지": 26, // 고차원 데이터 (2 + 4 + 9 + 11)
            "피바람의 손아귀": 5, // 에테르 응집물 (2 + 3)
        });
    });

    it("testGoalCoreSkillLevelAtOne", () => {
        const { result } = renderHook(() => useCoreSkillUpgradeCalculator());

        act(() => {
            result.current.setGoalCoreSkillLevel(1);
        });

        act(() => {
            result.current.calculateCoreSkillResources();
        });

        expect(result.current.usedCoreSkillResources).toEqual({});
    });
});
