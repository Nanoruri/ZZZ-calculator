import React from "react";
import Header from "../components/Header";
import CharacterSelect from "../components/CharacterSelect";
import Background from "../components/Background";
import "../styles/mainPage.css"; // CSS 파일 import

const MainPage: React.FC = () => {
    return (
        <div className="main-container">
            <Background />
            <Header />
            <div className="content">
                <CharacterSelect />
            </div>
        </div>
    );
};

export default MainPage;
