import {useNavigate} from "react-router-dom";



// 경로지정은 여기서
const useLocationNavigation = () => {
    const navigate = useNavigate();

    const goToMain = () => {
        navigate("/");
    };


    const goToRegistAgent = () => {
        navigate("/create-agent");
    }
    return {
        goToMain,
        goToRegistAgent,
    };
};



export default useLocationNavigation;