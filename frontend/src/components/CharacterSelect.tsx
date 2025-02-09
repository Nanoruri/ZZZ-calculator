import React from "react";
import "../styles/characterSelect.css";
import {useCharacters} from "../hooks/useCharacter.tsx";
import useLocationNavigation from "../hooks/useLocationNavi.tsx";

// const characters = [
//     { id: 1, name: "캐릭터 1", image: "/images/character1.png" },
//     { id: 2, name: "캐릭터 2", image: "/images/character2.png" },
//     { id: 3, name: "캐릭터 3", image: "/images/character3.png" },
//     { id: 4, name: "캐릭터 4", image: "/images/character4.png" },
// ];


const CharacterSelect: React.FC = () => {
    const { characters, loading, error } = useCharacters(); // 훅에서 데이터와 상태 가져오기
    if (loading) {
        return <p>로딩 중...</p>; // 로딩 상태일 때
    }

    if (error) {
        return <p>{error}</p>; // 에러 발생 시 에러 메시지 표시
    }

    return (
        <div className="character-grid">
            {characters.map((char) => (
                <div key={char.name} className="character-card">
                    <img src={char.role} alt={char.name} className="character-image" />
                    <p className="character-name">{char.name}</p>
                    <p className="character-role">{char.role}</p>
                    <p className="character-element">{char.elementType}</p>
                </div>
            ))}
        </div>
    );
};

export default CharacterSelect;
