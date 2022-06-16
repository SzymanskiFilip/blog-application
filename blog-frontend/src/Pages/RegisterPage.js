import {Link} from "react-router-dom";
import {useState} from "react";
import validator from "validator";

function RegisterPage(){

    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [rePassword, setRePassword] = useState("");

    //error, setError! = {} object

    const [usrTaken, setUsrTaken] = useState(false);
    const [agreed, setAgreed] = useState();
    const [error, setError] = useState([]);

    function register(){

        console.log(username, password)

        const registerDto = {
            username,
            email,
            password
        };

        if(agreed){
            setError([]);
            if(username.length > 5 && password.length > 5){
                if(rePassword === password){
                    if(validator.isEmail(email)){
                        console.log("email valid")            
                    } else {
                        setError(error => [...error, "The Email is not valid"]);
                    }
                } else {
                    setError(error => [...error, "Passwords don't match"]);
                }
            } else {
                setError(error => [...error, "Usernames and Passwords have to be longer than 5"]);
            }
        } else {
            setError(error => [...error, "You have to accept the term of services"]);
        }

        fetch("http://localhost:8080/register", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(registerDto)
        })
        .then(res => {
            setError(error => [...error, res.text()]);
        })

        //check if user is taken, else register
        //setUsrTaken(true);
    }

    return(
        <div>
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
            text-2xl">
                <p></p>
                <p className="col-span-1 text-center mx-4"><Link to={"/"}>Blog It!</Link></p>
                <div className="flex flex-row items-center justify-end">
                </div>
            </nav>


            <div className="absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 outline-none border-black border px-2 py-2 rounded">
                <h1 className="text-center">Register</h1>
                <div className="flex flex-col items-center justify-center">
                    <input placeholder="Username" type="text"
                    className={usrTaken ? "outline-none border-black border p-2 my-2 input-error" : "outline-none border-black border p-2 my-2 "} onChange={(e) => setUsername(e.target.value)}/>
                    <input placeholder="E-Mail" type="email" className="outline-none border-black border p-2 my-2" onChange={(e) => setEmail(e.target.value)}/>
                    <input placeholder="Password" type="password" className="outline-none border-black border p-2 my-2"  onChange={(e) => setPassword(e.target.value)}/>
                    <input placeholder="Repeat Password" type="password" className="outline-none border-black border p-2 my-2" onChange={(e) => setRePassword(e.target.value)}/>
                    <div className="flex flex-row items-center justify-around">
                        <input type="checkbox" name="" id="" checked={agreed} onChange={(e) => setAgreed(!agreed)}/>
                        <p className="text-sm">I agree with the term of services</p>
                    </div>
                    {
                        //usrTaken ? <p className="text-red-900">Username is already in use...</p> : <p></p>
                        error.map(m => {
                            return <h1 key={Math.random()}>{m}</h1>
                        })
                    }
                    <button className="bg-green-300 p-2 rounded text-black mt-2  hover:bg-green-400 transition duration-300" onClick={register}>Register</button>
                </div>
            </div>
        </div>
    )
}

export default RegisterPage;