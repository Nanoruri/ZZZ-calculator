export const convertElementType = (elementType: string) => {
    switch (elementType) {
        case "FIRE":
            return "불";
        case "PHYSICAL":
            return "물리";
        case "ICE":
            return "얼음";
        case "ELECTRIC":
            return "전기";
        case "ETHER":
            return "에테르";
        default:
            return "UNKNOWN";
    }
}