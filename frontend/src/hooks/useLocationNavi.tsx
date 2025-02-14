import {useNavigate} from "react-router-dom";



// 경로지정은 여기서
const useLocationNavigation = () => {
    const navigate = useNavigate();

    const goToMain = () => {
        navigate("/");
    };
    const goToCalc= () => {
        navigate("/calc");
    }

    const goToRegistAgent = () => {
        navigate("/create-agent");
    }

    const goToRegistMaterial = () => {
        navigate("/create-material");
    }
    return {
        goToMain,
        goToRegistAgent,
        goToRegistMaterial,
        goToCalc,
    };
};



export default useLocationNavigation;