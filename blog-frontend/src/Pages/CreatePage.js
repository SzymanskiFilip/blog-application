import { useContext } from "react";
import { useEffect } from "react";
import Navbar from "../Components/Navbar";
import { AuthContext } from "../Util/AuthContext";

function CreatePage({checkStatus}){

    const context = useContext(AuthContext);

    useEffect(() => {
        checkStatus();
    }, []);

    return (
        <Navbar status={context.authenticated} />
    )
}

export default CreatePage;