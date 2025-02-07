package me.jh.zenless.calc.entity;

public enum ElementType {

    // 물리, 불, 얼음, 전기, 에테르 속성

    PHYSICAL("물리"),
    FIRE("불"),
    ICE("얼음"),
    ELECTRIC("전기"),
    ETHER("에테르");

    private final String koreanName;

    ElementType(String koreanName) {
        this.koreanName = koreanName;
    }

    public static ElementType fromKoreanName(String name) {
        for (ElementType elementType : ElementType.values()) {
            if (elementType.koreanName.equals(name)|| elementType.name().equals(name.toUpperCase())) {
                return elementType;
            }
        }
        throw new IllegalArgumentException("해당하는 속성이 없습니다.");
    }
}
