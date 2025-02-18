import {useNavigate} from "react-router-dom";
import {Agent} from "../ts/api/getCharacterInfo.ts";



// 경로지정은 여기서
const useLocationNavigation = () => {
    const navigate = useNavigate();

    const goToMain = () => {
        navigate("/");
    };
    const goToCalc= (character:Agent) => {
        navigate("/calc", { state: { character } });
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