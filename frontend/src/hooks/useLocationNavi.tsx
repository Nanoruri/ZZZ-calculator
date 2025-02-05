import {useNavigate} from "react-router-dom";



// 경로지정은 여기서
const useLocationNavigation = () => {
    const navigate = useNavigate();

    const goToMain = () => {
        navigate("/");
    };

    return {
        goToMain,
    };
};



export default useLocationNavigation;