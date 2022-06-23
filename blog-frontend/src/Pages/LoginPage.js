/* eslint-disable react-hooks/exhaustive-deps */
import { useContext, useEffect, useState } from "react";
import { Link, useNavigate  } from "react-router-dom";
import { AuthContext } from "../Util/AuthContext";
import Spinner from "../Components/Spinner";

function LoginPage({checkStatus}){

    const context = useContext(AuthContext);
    const navigate =useNavigate();
    const controller = new AbortController();
    const signal = controller.signal;

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [spinner, setSpinner] = useState(false);
    const [auth, setAuth] = useState(false);
    const [agreed, setAgreed] = useState(false);

    useEffect(() => {
        checkStatus();
        addKeyListener();
    },[]);

    function addKeyListener(){
        const keyboard = window;
        keyboard.addEventListener("keydown", e =>{
            if(e.key === "Enter"){
                login();
            }
        })
    }


    async function login(){
        if(username.length > 0 && password.length > 0){
            setSpinner(true);
            console.log(`username: ${username}, password: ${password}`);
            const credentials = {
                username: username,
                password: password,
                remember_me: agreed
            };
      
            setTimeout(() => {
                if(!auth){
                    controller.abort();
                    setSpinner(false);
                    setPassword("");
                    let passwordInput = document.getElementById("password");
                    passwordInput.value = "";
                }
            
            }, 15000);
        
            fetch("http://localhost:8080/login", {
                method: "POST",
                mode: "cors",
                credentials: "include",
                signal: signal,
                body: JSON.stringify(credentials)
            }).then(res => {
                if(res.status === 200){
                    setAuth(true);
                    setSpinner(false);
                    context.setAuthenticated(true);
                    navigate(-1);
                }
            })
        }
    }

    return(
        <div >

            {
                spinner
                ?
                <div className="absolute top-1/2 left-1/2 transform -translate-x-1/2
                                -translate-y-1/2 z-10 bg-white h-64 w-80 flex flex-row items-center justify-center rounded">
                    <Spinner spinnerText={"logging in..."}/>
                </div>
                :
                <></>
            }

            

            <nav className="
            bg-stone-900 
            h-14 
            text-white
            flex flex-row
            justify-center
            items-center
            text-2xl
            ">
            <Link to="/">Blog It!</Link>
            </nav>

            <div className="absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2">
                <div className="
                flex flex-col
                bg-white
                border-2
                border-black
                rounded
                items-center
                justify-center
                px-4
                py-4
                ">
                <p className="text-center text-black text-2xl">Login</p>
                {/*TODO: ADD INPUT STYLING TO CUSTOM CLASS! */}
                <input type="text" className="outline-none border-black border px-2" placeholder="Username"
                onChange={(e) => setUsername(e.target.value)}/>

                <input type="password" className="outline-none border-black border px-2 my-2" id="password" required placeholder="Password"
                onChange={(e) => setPassword(e.target.value)}
                />

                <div className="flex flex-row items-center justify-center text-sm">
                    <input type="checkbox" name="" id="" checked={agreed} onChange={(e) => setAgreed(!agreed)}/>
                    <h1>Remember me</h1>
                </div>
                

                <button className="border-black border px-px rounded" onClick={login} id="login">Login</button>
                <p className="text-center text-xs mt-2">Don't have an account?</p>
                <Link className="text-center text-xs hover:cursor-pointer hover:text-blue-500" to="/register">Create one here</Link>
                </div>
            </div>
        </div>
    )
}

export default LoginPage;