import Background from "../components/Background.tsx";
import Header from "../components/Header.tsx";
import {LevelCalculatingBox} from "../components/calculate/LevelCalculatingBox.tsx";
import "../styles/mainPage.css";
import React from "react";

const calculator : React.FC = () => {
    return (
        <div className="main-container">
            <Background />
            <Header />
            <div className="content">
                <LevelCalculatingBox />
        </div>
    </div>
    );
};

export default calculator;