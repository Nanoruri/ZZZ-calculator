import React from "react";
import Background from "../components/Background.tsx";
import Header from "../components/Header.tsx";
import RegistAgent from "../components/RegistAgent.tsx";
import "../styles/registAgent.css";

const calcPage : React.FC = () => {
    return (
        <div className="main-container">
            <Background />
            <Header />
            <div className="content">
                <RegistAgent />
            </div>
        </div>
    );
};

export default calcPage;