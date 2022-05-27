import { useContext } from "react";
import { AuthContext } from "./AuthContext";

function RequireAuth({children}){
    
    const context = useContext(AuthContext);

    return(
        context ? children : <p>you have to log in</p>
    )
}

export default RequireAuth;