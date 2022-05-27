import { useContext } from "react";
import { AuthContext } from "./AuthContext";

function BlockAuth({children}){

    const context = useContext(AuthContext);

    return(
        context ? <p>you are logged in</p> : children
    )
}

export default BlockAuth;