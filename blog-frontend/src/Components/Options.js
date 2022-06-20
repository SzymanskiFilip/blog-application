import {useEffect, useState} from "react";
import { useNavigate } from "react-router-dom";

function Options({option}){

    const [posts, setPosts] = useState([]);

    const navigate = useNavigate();

    useEffect(() => {
        fetch("http://localhost:8080/myPosts", {
            method: "GET",
            mode: "cors",
            credentials: "include",
            headers: {
                "Content-Type": "application/json"
            }
        })
        .then(res => res.json())
        .then(res => setPosts(res))
    });

    let length = 12;

    if(option === 1){
        return (
            <div className="mt-4 text-center">
                
            </div>
        )
    }
    if(option === 2){
        console.log(2)
        return (
            <div>
                {
                    posts.map((post) => {
                        return (
                            <div key={post.id} className="flex flex-col m-4 items-center bg-gray-400 rounded">
                                <h1>Title: {post.title}</h1>
                                <h1>Likes: {post.likes}</h1>
                                <button onClick={() => navigate(`/post/${post.id}`)}>Open</button>
                            </div>
                        )
                    })
                }
            </div>
        )
    }
}

export default Options;