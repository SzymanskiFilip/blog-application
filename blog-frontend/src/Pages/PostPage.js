/* eslint-disable react-hooks/exhaustive-deps */
import { useContext, useEffect } from "react";
import { useParams, useNavigate} from "react-router-dom";
import Navbar from "../Components/Navbar";
import { AuthContext } from "../Util/AuthContext";
import {useState} from "react";

function PostPage(){
    const postId = useParams();

    const context = useContext(AuthContext);
    const [postData, setPostData] = useState({});
    const navigate = useNavigate();

    async function getPost(){
        await fetch(`http://localhost:8080/post/${postId.id}`)
        .then(res => res.json())
        .then(res => setPostData(res))
        
    }

    useEffect(() => {
        getPost();
    },[]);


    function likePost(){
        if(context.authenticated === true){
            
        } else {
            navigate("/login", {url: window.location.href});
        }
    }


    return(
        <div>
        <Navbar status={context}/>
        <div className="flex flex-col items-center justify-cetner mb-12">
        <img src={'https://wallpaperaccess.com/full/2029165.jpg'} alt={process.env.PUBLIC_URL + `images/${postData.image_name}`}
        className="bg-img-full"
        />
            <div className="flex flex-col justify-between bg-gray-100 rounded">
                 
                <div className="mx-4">
                    <h1 className="text-2xl sm:text-5xl mt-2 mb-2">{postData.title}</h1>
                    <p>{postData.body}</p>
                </div>

                <div className="flex flex-row items-center justify-between mx-4 mt-2 mb-2">
                    <div className="flex flex-row px-1 py-px sm:px-2 sm:py-2 bg-gray-200 rounded">
                        <h1 className="text-sm">Likes: {postData.likes}</h1>
                    </div>
                    <div>
                        <h1 className="px-1 py-px sm:px-2 sm:py-2 bg-gray-200 transition duration-300 rounded hover:cursor-pointer hover:bg-green-300" onClick={likePost}>
                        Like</h1>
                    </div>
                </div>

            </div>
        </div>
        </div>
    )
}

export default PostPage;