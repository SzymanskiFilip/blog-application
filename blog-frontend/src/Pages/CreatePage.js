/* eslint-disable react-hooks/exhaustive-deps */
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
        <div>
            <Navbar status={context.authenticated} />
            <div className="flex flex-col items-center justify-center mt-20">
                <div className="flex flex-col
                bg-white
                border-2
                border-black
                rounded
                items-center
                justify-center
                px-4
                py-4">
                <h1>Title</h1>
                <input type="text" className="outline-none border-black border px-2"/>
                <h1>Body</h1>
                <textarea name="" id="" cols="50" rows="10" placeholder="Input your text here..." className="outline-none border-black border px-2"></textarea>
                <h1>Upload Background Image:</h1>
                <input type="file" id="fileUpload" name="filename"/>
            </div>
            </div>
        </div>
    )
}

export default CreatePage;