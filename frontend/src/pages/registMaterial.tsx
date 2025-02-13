import React from "react";
import Background from "../components/Background.tsx";
import Header from "../components/Header.tsx";
import ResourceInputFields from "../components/material/ResourceInputFields.tsx";

const RegisterMaterialPage: React.FC = () => {

    return (
        <div>
            <Background />
            <Header />
            <ResourceInputFields />
        </div>
    );
}

export default RegisterMaterialPage;
