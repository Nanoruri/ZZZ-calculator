import React from "react";
import "../styles/characterSelect.css";

const characters = [
    { id: 1, name: "캐릭터 1", image: "/images/character1.png" },
    { id: 2, name: "캐릭터 2", image: "/images/character2.png" },
    { id: 3, name: "캐릭터 3", image: "/images/character3.png" },
    { id: 4, name: "캐릭터 4", image: "/images/character4.png" },
];

const CharacterSelect: React.FC = () => {
    return (
        <div className="character-grid">
            {characters.map((char) => (
                <div key={char.id} className="character-card">
                    <img src={char.image} alt={char.name} className="character-image" />
                    <p className="character-name">{char.name}</p>
                </div>
            ))}
        </div>
    );
};

export default CharacterSelect;
