import React from "react";
import ReactDOM from "react-dom/client";
import Path from "./path.tsx";
import "../styles/index.css";
import {BrowserRouter as Router} from "react-router-dom";

ReactDOM.createRoot(document.getElementById("root") as HTMLElement).render(
    <React.StrictMode>
        <Router>
            <Path/>
        </Router>
    </React.StrictMode>
);