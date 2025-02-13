import React from "react";
import Background from "../components/Background.tsx";
import Header from "../components/Header.tsx";
import ResourceInputFields from "../components/material/ResourceInputFields.tsx";
import "../styles/material/registMaterial.css"; // 스타일 파일 추가

const RegisterMaterialPage: React.FC = () => {
    return (
        <div className="register-material-page">
            <Background />
            <Header />
            <div className="content">
                <ResourceInputFields />
            </div>
        </div>
    );
}

export default RegisterMaterialPage;