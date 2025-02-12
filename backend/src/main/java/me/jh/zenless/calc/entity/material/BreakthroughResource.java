package me.jh.zenless.calc.entity.material;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.jh.zenless.calc.entity.ElementType;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BREAKTHROUGH_RESOURCE")
public class BreakthroughResource {


    @Id
    @Column(name = "ITEM_ID", unique = true)
    private String name; // 아이템 이름 (예: 초급 강공 휘장, 기본 부품)

    @Enumerated(EnumType.STRING)
    private ElementType attribute; // 속성 (예: 물리, 전자 등)

    // 아이템 타입은 프론트에서 선택할 수 있도록 하기
    @Column(name = "ITEM_TYPE")
    private String type; // 부품, 휘장 구분


    @Column(name = "ITEM__START_LEVEL")
    private int levelRangeStart; // 레벨 시작 (예: 1)

    @Column(name = "ITEM_END_LEVEL")
    private int levelRangeEnd; // 레벨 끝 (예: 20)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESOURCE_CORE_ID")
    @JsonBackReference
    private ResourceCore resourceCore;
}
