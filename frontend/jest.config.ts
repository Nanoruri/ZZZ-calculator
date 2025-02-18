import type { Config } from "jest";

const config: Config = {
    preset: "ts-jest",
    testEnvironment: "jest-environment-jsdom",  // DOM을 사용하는 테스트에 적합
    transform: {
        "^.+\\.(ts|tsx)$": [
            "ts-jest",
            {
                tsconfig: "./tsconfig.test.json"  // tsconfig.test.json을 적용
            }
        ],
    },
    moduleNameMapper: {
        "\\.(css|less|scss|sass)$": "identity-obj-proxy", // CSS 파일 처리
    },
};

export default config;
