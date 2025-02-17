import React from "react";
import {Route, Routes} from "react-router-dom";
import MainPage from "../pages/mainPage.tsx";
import RegistAgent from "../pages/registAgent.tsx";
import RegistMaterial from "../pages/registMaterial.tsx";
import CalcPage from "../pages/calculator.tsx";

const Path: React.FC = () => {
    return (
        <Routes>
            <Route path="/" element={<MainPage/>}/>
            <Route path="/calc" element={<CalcPage/>}/>
            <Route path="/create-agent" element={<RegistAgent/>}/>
            <Route path="/create-material" element={<RegistMaterial/>}/>
        </Routes>
    );
};

export default Path;