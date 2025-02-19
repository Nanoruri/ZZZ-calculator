import { useCharacterInfo } from "../hooks/useCharacter";  // useCharacter 훅을 임포트
import React from "react";


export const CharacterInfo: React.FC = () => {
    const character = useCharacterInfo();  // useCharacter 훅을 사용해 캐릭터 정보 불러오기

    if (!character) return <p>캐릭터 정보가 없습니다.</p>; // 캐릭터 정보가 없으면 처리

    return (
        <div className="character-info">
            <h3>선택한 캐릭터</h3>
            <p>이름: {character.name}</p>
            <p>역할: {character.role}</p>
            <p>속성: {character.elementType}</p>
        </div>
    );
};
