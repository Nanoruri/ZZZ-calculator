import React from "react";
import "../styles/header.css";
import logo from "../public/zenlessLogo.png";
import useLocationNavigation from "../hooks/useLocationNavi";


const Header: React.FC = () => {
    const { goToMain } = useLocationNavigation();

    return (
        <header className="header"  onClick={goToMain}>
            <img src={logo} alt="Zenless Logo" className="header-logo" />
            <span>Zenless-Calculator</span>
        </header>
    );
};

export default Header;
