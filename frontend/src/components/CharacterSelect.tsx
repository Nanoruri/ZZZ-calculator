import React from "react";
import "../styles/characterSelect.css";
import {useCharacters} from "../hooks/useCharacter.tsx";
import useLocationNavigation from "../hooks/useLocationNavi.tsx";
import {Agent} from "../ts/api/getCharacterInfo.ts";
import {useNavigate} from "react-router-dom";

// const characters = [
//     { id: 1, name: "캐릭터 1", image: "/images/character1.png" },
//     { id: 2, name: "캐릭터 2", image: "/images/character2.png" },
//     { id: 3, name: "캐릭터 3", image: "/images/character3.png" },
//     { id: 4, name: "캐릭터 4", image: "/images/character4.png" },
// ];


const CharacterSelect: React.FC = () => {
    const { goToCalc } = useLocationNavigation();
    const { characters, loading, error } = useCharacters(); // 캐릭터 데이터 및 상태 가져오기

    if (loading) return <p>로딩 중...</p>; // 로딩 상태 표시
    if (error) return <p>{error}</p>; // 에러 발생 시 메시지 표시

    const handleCharacterSelect = (agent: Agent) => {
        goToCalc(agent); // goToCalc 호출하면서 캐릭터 데이터 전달
    };

    return (
        <div className="character-grid">
            {characters.map((char) => (
                <div key={char.name} className="character-card" onClick={() => handleCharacterSelect(char)}>
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