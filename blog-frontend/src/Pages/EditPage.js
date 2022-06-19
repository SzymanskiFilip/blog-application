import { useContext, useEffect, useState } from "react";
import {useLocation} from "react-router-dom";
import { AuthContext } from "../Util/AuthContext";
import Navbar from "../Components/Navbar";

function EditPage(){

    let context = useContext(AuthContext);
    const location = useLocation();
    const postData = location.state;

    const [title, setTitle] = useState(postData.title);
    const [body, setBody] = useState(postData.body);
    const [imageUpload, setImageUpload] = useState(null);
    const [preview, setPreview] = useState("");

    useEffect(() => {
        if(imageUpload){
            const reader = new FileReader();
            reader.onloadend = () => {
                setPreview(reader.result);
            }
            reader.readAsDataURL(imageUpload);
        } else {
            setPreview(null);
        }
    }, [imageUpload]);

    async function save(){
        if(imageUpload == null){
            saveWithoutImage();
        } else {
            saveWithImage();
        }
    }

    async function saveWithoutImage(){
        console.log("withou")
    }

    async function saveWithImage(){
        console.log("with")
    }

    return(
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
                <h1 className="text-3xl mb-2">Edit</h1>
                <h1>Title</h1>
                <input type="text" className="outline-none border-black border px-2" value={title} onChange={(e) => setTitle(e.target.value)}/>
                <h1>Body</h1>
                <textarea cols="50" rows="10" placeholder="Input your text here..." className="outline-none border-black border px-2" value={body} onChange={(e) => setBody(e.target.value)}></textarea>
                <h1>Upload Background Image:</h1>
                <input type="file" accept="image/*" name="fileupload" onChange={(event) => setImageUpload(event.target.files[0])}/>
                <h1>Image Preview</h1>
                {
                    preview
                    ?
                    <img src={preview} className="h-24" alt="preview"/>
                    :
                    <img src={`images/${postData.image_name}`} className="h-24" alt="preview"/>
                }
                
                <button onClick={save}>SAVE</button>
            </div>
            </div>
        </div>
    )
}

export default EditPage;