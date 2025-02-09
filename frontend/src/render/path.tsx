import React from "react";
import {Route, Routes} from "react-router-dom";
import MainPage from "../pages/mainPage.tsx";
import RegistAgent from "../pages/registAgent.tsx";

const Path: React.FC = () => {
    return (
        <Routes>
            <Route path="/" element={<MainPage/>}/>
            <Route path="/create-agent" element={<RegistAgent/>}/>
        </Routes>
    );
};

export default Path;