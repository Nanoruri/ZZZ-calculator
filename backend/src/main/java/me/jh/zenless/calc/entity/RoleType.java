package me.jh.zenless.calc.entity;

public enum RoleType {

    //강공, 격파, 이상, 지원, 방어

    STRONG("강공"),
    BREAK("격파"),
    ABNORMAL("이상"),
    SUPPORT("지원"),
    DEFENSE("방어");


    private final String koreanName;

    RoleType(String koreanName) {
        this.koreanName = koreanName;
    }


    public static RoleType fromKoreanName(String name) {
        for (RoleType roleType : RoleType.values()) {
            if (roleType.koreanName.equals(name)|| roleType.name().equals(name.toUpperCase())) {
                return roleType;
            }
        }
        throw new IllegalArgumentException("해당하는 역할이 없습니다.");
    }

}
