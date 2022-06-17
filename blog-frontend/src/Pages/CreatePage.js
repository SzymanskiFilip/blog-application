/* eslint-disable react-hooks/exhaustive-deps */
import { useContext, useState } from "react";
import { useEffect } from "react";
import Navbar from "../Components/Navbar";
import { AuthContext } from "../Util/AuthContext";

function CreatePage({checkStatus}){

    const context = useContext(AuthContext);

    const [title, setTitle] = useState("");
    const [body, setBody] = useState("");
    useEffect(() => {
        checkStatus();
    }, []);

    const [imageUpload, setImageUpload] = useState(null);
    const uploadImage = () => {
        if(imageUpload == null) return;
        
        let formData = new FormData();
        formData.set("file", imageUpload);
        fetch("http://localhost:8080/create-post", {
            method: "POST",
            credentials: "include",
            mode: "cors",
            onUploadProgress: progressEvent => {
                //console.log(first)
            }
        })
    };

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
                <input type="file" accept="image/*" name="fileupload" onChange={(event) => setImageUpload(event.target.files[0])}/>
                <button onClick={uploadImage}>send</button>
            </div>
            </div>
        </div>
    )
}

export default CreatePage;