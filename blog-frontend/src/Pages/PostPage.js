import { useContext } from "react";
import { useParams } from "react-router-dom";
import Navbar from "../Components/Navbar";
import { AuthContext } from "../Util/AuthContext";

function PostPage(){
    const postId = useParams();
    console.log(postId)

    const context = useContext(AuthContext);

    return(
        <div>
        <Navbar status={context}/>
        <div className="flex flex-col items-center justify-cetner mt-4 mb-12">
            <div className="w-screen sm:w-10/12 flex flex-col justify-between bg-gray-100 px-2 py-2 rounded">
                <div>
                    <img src={"https://images.unsplash.com/photo-1496442226666-8d4d0e62e6e9?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8&w=1000&q=80"} alt="post banner"
                    className="post-bg-img rounded"
                    />
                </div>
            
                <div className="mx-4">
                    <h1 className="text-2xl sm:text-5xl mt-2 mb-2">{"Why New York is beautiful"}</h1>
                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Laboriosam aperiam asperiores ipsam perferendis est, pariatur, quis non adipisci porro fugit voluptatum autem iure rem saepe amet aliquid! Impedit, eveniet modi. Lorem ipsum dolor, sit amet consectetur adipisicing elit. Nisi praesentium nihil eveniet quis in impedit, expedita atque laborum odit harum nam aliquid rerum quia illum mollitia, quos est, dolorum tempora. Lorem ipsum dolor sit amet consectetur adipisicing elit. Eius ipsum repellat optio obcaecati magnam cum quidem iste, nam, minima corrupti reprehenderit fugit placeat consequatur. Quaerat quod tenetur repellat vero qui?
                    Lorem ipsum dolor sit amet consectetur, adipisicing elit. Optio doloremque ducimus aliquid obcaecati voluptatem modi, reprehenderit assumenda dicta vel iste rem natus nesciunt beatae porro in a sint ut? Ipsum?
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Laboriosam aperiam asperiores ipsam perferendis est, pariatur, quis non adipisci porro fugit voluptatum autem iure rem saepe amet aliquid! Impedit, eveniet modi. Lorem ipsum dolor, sit amet consectetur adipisicing elit. Nisi praesentium nihil eveniet quis in impedit, expedita atque laborum odit harum nam aliquid rerum quia illum mollitia, quos est, dolorum tempora. Lorem ipsum dolor sit amet consectetur adipisicing elit. Eius ipsum repellat optio obcaecati magnam cum quidem iste, nam, minima corrupti reprehenderit fugit placeat consequatur. Quaerat quod tenetur repellat vero qui?
                    Lorem ipsum dolor sit amet consectetur, adipisicing elit. Optio doloremque ducimus aliquid obcaecati voluptatem modi, reprehenderit assumenda dicta vel iste rem natus nesciunt beatae porro in a sint ut? Ipsum?
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Laboriosam aperiam asperiores ipsam perferendis est, pariatur, quis non adipisci porro fugit voluptatum autem iure rem saepe amet aliquid! Impedit, eveniet modi. Lorem ipsum dolor, sit amet consectetur adipisicing elit. Nisi praesentium nihil eveniet quis in impedit, expedita atque laborum odit harum nam aliquid rerum quia illum mollitia, quos est, dolorum tempora. Lorem ipsum dolor sit amet consectetur adipisicing elit. Eius ipsum repellat optio obcaecati magnam cum quidem iste, nam, minima corrupti reprehenderit fugit placeat consequatur. Quaerat quod tenetur repellat vero qui?
                    Lorem ipsum dolor sit amet consectetur, adipisicing elit. Optio doloremque ducimus aliquid obcaecati voluptatem modi, reprehenderit assumenda dicta vel iste rem natus nesciunt beatae porro in a sint ut? Ipsum?
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Laboriosam aperiam asperiores ipsam perferendis est, pariatur, quis non adipisci porro fugit voluptatum autem iure rem saepe amet aliquid! Impedit, eveniet modi. Lorem ipsum dolor, sit amet consectetur adipisicing elit. Nisi praesentium nihil eveniet quis in impedit, expedita atque laborum odit harum nam aliquid rerum quia illum mollitia, quos est, dolorum tempora. Lorem ipsum dolor sit amet consectetur adipisicing elit. Eius ipsum repellat optio obcaecati magnam cum quidem iste, nam, minima corrupti reprehenderit fugit placeat consequatur. Quaerat quod tenetur repellat vero qui?
                    Lorem ipsum dolor sit amet consectetur, adipisicing elit. Optio doloremque ducimus aliquid obcaecati voluptatem modi, reprehenderit assumenda dicta vel iste rem natus nesciunt beatae porro in a sint ut? Ipsum?
                    Lorem ipsum dolor sit amet consectetur adipisicing elit. Laboriosam aperiam asperiores ipsam perferendis est, pariatur, quis non adipisci porro fugit voluptatum autem iure rem saepe amet aliquid! Impedit, eveniet modi. Lorem ipsum dolor, sit amet consectetur adipisicing elit. Nisi praesentium nihil eveniet quis in impedit, expedita atque laborum odit harum nam aliquid rerum quia illum mollitia, quos est, dolorum tempora. Lorem ipsum dolor sit amet consectetur adipisicing elit. Eius ipsum repellat optio obcaecati magnam cum quidem iste, nam, minima corrupti reprehenderit fugit placeat consequatur. Quaerat quod tenetur repellat vero qui?
                    Lorem ipsum dolor sit amet consectetur, adipisicing elit. Optio doloremque ducimus aliquid obcaecati voluptatem modi, reprehenderit assumenda dicta vel iste rem natus nesciunt beatae porro in a sint ut? Ipsum?
                    </p>
                </div>

                <div className="flex flex-row items-center justify-between mx-4 mt-2 mb-2">
                    <div className="flex flex-row px-1 py-px sm:px-2 sm:py-2 bg-gray-200 rounded">
                        <h1 className="text-sm mx-1">Views: 500</h1>
                        <h1 className="text-sm">Likes: 1288</h1>
                    </div>
                    <div>
                        <h1 className="px-1 py-px sm:px-2 sm:py-2 bg-gray-200 transition duration-300 rounded hover:cursor-pointer hover:bg-green-300">
                        Like</h1>
                    </div>
                </div>

            </div>
        </div>
        </div>
    )
}

export default PostPage;