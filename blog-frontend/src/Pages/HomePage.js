import { useContext, useEffect, useState } from "react";
import Navbar from "../Components/Navbar";
import { AuthContext } from "../Util/AuthContext";
import Post from "../Components/Post";

function HomePage(){

    const context = useContext(AuthContext);
    const [posts, setPosts] = useState([]);

    async function getPosts(){
        await fetch("http://localhost:8080/posts")
        .then(res => res.json())
        .then(data => setPosts(data.content))
    }
    
    console.log(posts)

    useEffect(() => {
        getPosts();
    }, []);

    return(
        <div>
            <Navbar status={context.authenticated}/>
            <div 
            className="flex flex-col items-center justify-center my-10">
            {
                posts.map((p) => {
                    return <Post data={p} key={p.id}/>
                })
            }
            </div>
        </div>
    )
}

export default HomePage;