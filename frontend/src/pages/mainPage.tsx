import React from "react";
import Header from "../components/Header";
import CharacterSelect from "../components/CharacterSelect";
import Background from "../components/Background";
import "../styles/mainPage.css";
import useLocationNavigation from "../hooks/useLocationNavi.tsx"; // CSS 파일 import

const MainPage: React.FC = () => {
    const { goToRegistAgent } = useLocationNavigation();
    return (
        <div className="main-container">
            <Background />
            <Header />
            <div className="content">
                <CharacterSelect/>
                <button className="register-button" onClick={goToRegistAgent}>
                    캐릭터 등록
                </button>
            </div>
        </div>
    );
};

export default MainPage;
