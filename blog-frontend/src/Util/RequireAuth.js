import { useContext } from "react";
import { Link, Navigate, useNavigate } from "react-router-dom";
import { AuthContext } from "./AuthContext";

function RequireAuth({children}){
    
    const context = useContext(AuthContext);

    return(
        context.authenticated ? children : <Navigate to="/login"/>
    )
}

export default RequireAuth;