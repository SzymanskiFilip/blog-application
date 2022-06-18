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

    async function post(){
        console.log(title, body)
        fetch("http://localhost:8080/create-post-data", {
            method: "POST",
            credentials: "include",
            mode: "cors",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({title, body})
        })
        .then(res => {
            if(res.status === 200){
                return res.json()
            }
        })
        .then(res => {
            uploadImage(res.id);
        })
        
    }

    const uploadImage = (id) => {
        console.log("UPLOAD FUNCTION")
        console.log(id)
        if(imageUpload == null) return;
        let formData = new FormData();
        formData.set("file", imageUpload);
        formData.set("id", id);
        fetch("http://localhost:8080/create-post-image", {
            method: "POST",
            credentials: "include",
            mode: "cors",
            body: formData,
            onUploadProgress: (p) => {
                console.log(p)
            }
        }).then(res => console.log(res))
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
                <input type="text" className="outline-none border-black border px-2" onChange={(e) => setTitle(e.target.value)}/>
                <h1>Body</h1>
                <textarea cols="50" rows="10" placeholder="Input your text here..." onChange={(e) => setBody(e.target.value)} className="outline-none border-black border px-2"></textarea>
                <h1>Upload Background Image:</h1>
                <input type="file" accept="image/*" name="fileupload" onChange={(event) => setImageUpload(event.target.files[0])}/>
                <button onClick={post}>send</button>
            </div>
            </div>
        </div>
    )
}

export default CreatePage;