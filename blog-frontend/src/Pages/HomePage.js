import { useContext, useEffect, useState } from "react";
import Navbar from "../Components/Navbar";
import { AuthContext } from "../Util/AuthContext";
import Post from "../Components/Post";

function HomePage({checkStatus}){

    const context = useContext(AuthContext);
    const [posts, setPosts] = useState([]);

    async function getPosts(){
        await fetch("http://localhost:8080/posts")
        .then(res => res.json())
        .then(data => setPosts(data.content))
    }

    useEffect(() => {
        getPosts();
        checkStatus();
    }, []);

    return(
        <div>
            <Navbar status={context.authenticated}/>
            <div 
            className="flex flex-col items-center justify-center my-10">
            {
                posts.length > 0
                ?
                posts.map((p) => {
                    return <Post data={p} key={p.id}/>
                })
                :
                <h1>No posts found</h1>
            }
            </div>
        </div>
    )
}

export default HomePage;