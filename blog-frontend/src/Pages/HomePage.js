import { useContext } from "react";
import Navbar from "../Components/Navbar";
import { AuthContext } from "../Util/AuthContext";
import Post from "../Components/Post";

function HomePage(){

    const context = useContext(AuthContext);

    const dummyPost = {
        title: "Why New York is so beautiful!",
        body: "Lorem ipsum dolor sit amet consectetur adipisicing elit. Similique explicabo voluptas ipsum fuga repellendus suscipit sequi, consectetur iusto magnam aperiam. Qui rerum dignissimos sunt! A, quam? Expedita, natus. Illum, rerum!",
        image: "https://images.unsplash.com/photo-1496442226666-8d4d0e62e6e9?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8&w=1000&q=80",
        likes: 542
    };

    let list = [];
    list.push(dummyPost);

    return(
        <div>
            <Navbar status={context}/>
            <Post data={dummyPost}/>
        </div>
    )
}

export default HomePage;