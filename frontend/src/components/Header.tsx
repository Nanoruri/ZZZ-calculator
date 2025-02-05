import React from "react";
import "../styles/header.css";
import logo from "../public/zenlessLogo.png";


const Header: React.FC = () => {

    return (
        <header className="header" >
            <img src={logo} alt="Zenless Logo" className="header-logo" />
            <span>Zenless-Calculator</span>
        </header>
    );
};

export default Header;
