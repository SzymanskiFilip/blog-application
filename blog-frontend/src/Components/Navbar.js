import { Link, useNavigate } from "react-router-dom";
import { AuthContext } from "../Util/AuthContext";
import {useContext} from "react";

function Navbar({status}){

    const navigate = useNavigate();
    const context = useContext(AuthContext);

    function logoutRequest(){
        fetch("http://localhost:8080/logout", {
            method: "POST",
            mode: "cors",
            credentials: "include"
        })
        .then(res => console.log(res))
        .then(navigate('/'));
    }


    function handleClick(e){
        if(e === "LOGIN"){
            navigate("/login");
        } else if(e === "LOGOUT"){
            logoutRequest();
            context.setAuthenticated(false);
        }
    }

    function navigateToWritePage(){

        navigate("/create");
    }

    return(
        <nav className="
            bg-stone-900 
            h-14 
            text-white
            flex
            flex-row
            sm:grid
            sm:grid-cols-3
            sm:text-2xl
            items-center
            justify-center
            text-2xl
            ">
            <p></p>
            <p className="col-span-1 text-center mx-4"><Link to={"/"}>Blog It!</Link></p>
            <div className="flex flex-row items-center justify-end">
                {
                    status
                    ?
                    <p className="mx-4 hover:cursor-pointer" onClick={navigateToWritePage}>Write</p>
                    :
                    <></>
                }
                <p className="mx-4 hover:cursor-pointer" onClick={(e) => handleClick(e.target.textContent)}>{status ? "LOGOUT" : "LOGIN"}</p>
            </div>
        </nav>
    )
}

export default Navbar;