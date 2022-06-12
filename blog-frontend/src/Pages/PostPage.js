/* eslint-disable jsx-a11y/img-redundant-alt */
/* eslint-disable react-hooks/exhaustive-deps */
import { useContext, useEffect } from "react";
import { useParams, useNavigate} from "react-router-dom";
import Navbar from "../Components/Navbar";
import { AuthContext } from "../Util/AuthContext";
import {useState} from "react";

function PostPage({checkStatus}){
    const postId = useParams();

    //TODO: Serve images from server in the future!
    //https://stackoverflow.com/questions/44611047/get-image-from-server-and-preview-it-on-client

    const context = useContext(AuthContext);
    const [postData, setPostData] = useState({});
    const navigate = useNavigate();
    const [likedState, setLikedState] = useState();

    async function getPost(){
        await fetch(`http://localhost:8080/post/${postId.id}`, {
            method: "GET",
            mode: "cors",
            credentials: "include"
        })
        .then(res => res.json())
        .then(res => setData(res))
    }

    function setData(response){
        setPostData(response);
        setLikedState(response.liked_status);
    }

    async function likeRequest(){
        fetch("http://localhost:8080/like", {
            method: "POST",
            mode: "cors",
            credentials: "include",
            headers:{
                "Content-Type": "application/json"
            },
            body: JSON.stringify(postId)
        })
        .then(res => console.log(res))
    }

    useEffect(() => {
        getPost();
        checkStatus();
    },[]);


    function likePost(){
        if(context.authenticated === true){
            if(likedState){
                setLikedState(false);
                likeRequest();
            } else {
                setLikedState(true);
                likeRequest();
            }
        } else {
            navigate("/login", {url: window.location.href});
        }
    }

    return(
        <div>
        <Navbar status={context.authenticated}/>
        <div className="flex flex-col items-center justify-cetner mb-12">
        <img src={`/images/${postData.image_name}.jpg`} alt={"picture"}
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
                        <h1 className={
                            likedState ? "px-1 py-px sm:px-2 sm:py-2 bg-gray-200 transition duration-300 rounded hover:cursor-pointer hover:bg-red-300"
                            :
                            "px-1 py-px sm:px-2 sm:py-2 bg-gray-200 transition duration-300 rounded hover:cursor-pointer hover:bg-green-300"
                        } onClick={likePost}>
                        {likedState ? "Unlike" : "Like"}</h1>
                    </div>
                </div>

            </div>
        </div>
        </div>
    )
}

export default PostPage;