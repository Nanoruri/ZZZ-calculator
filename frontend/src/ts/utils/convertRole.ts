// 에이전트의 role을 변환한다.

export const convertRole = (role: string) => {
    switch (role) {
        case "STRONG":
            return "강공";
        case "BREAK":
            return "격파";
        case "ABNORMAL":
            return "이상";
        case "SUPPORT":
            return "지원";
        case "DEFENSE":
            return "방어";
        default:
            return "UNKNOWN";
    }
}