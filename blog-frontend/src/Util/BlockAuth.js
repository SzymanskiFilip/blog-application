import { useContext } from "react";
import { AuthContext } from "./AuthContext";
import {Navigate} from "react-router-dom";

function BlockAuth({children}){


    const context = useContext(AuthContext);

    return(
        context.authenticated ? <Navigate to="/"/> : children
    )
}

export default BlockAuth;