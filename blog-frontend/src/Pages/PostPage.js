import { useParams } from "react-router-dom";

function PostPage(){
    const postId = useParams();
    console.log(postId)
    return(
        <h1>POST PAGE</h1>
    )
}

export default PostPage;