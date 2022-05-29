function Post({data}){
    return(
        <div className="w-8/12 flex flex-col justify-between bg-gray-100 px-2 py-2 rounded">
            <div>
                <img src={data.image} alt="post banner"
                className="post-bg-img rounded"
            />
            </div>
            
            <div className="mx-4">
                <h1 className="text-2xl sm:text-5xl mt-2 mb-2">{data.title}</h1>
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Laboriosam aperiam asperiores ipsam perferendis est, pariatur, quis non adipisci porro fugit voluptatum autem iure rem saepe amet aliquid! Impedit, eveniet modi. Lorem ipsum dolor, sit amet consectetur adipisicing elit. Nisi praesentium nihil eveniet quis in impedit, expedita atque laborum odit harum nam aliquid rerum quia illum mollitia, quos est, dolorum tempora. Lorem ipsum dolor sit amet consectetur adipisicing elit. Eius ipsum repellat optio obcaecati magnam cum quidem iste, nam, minima corrupti reprehenderit fugit placeat consequatur. Quaerat quod tenetur repellat vero qui? <a href="2" className="text-blue-500">read more...</a></p>

            </div>

            <div className="flex flex-row items-center justify-between mx-4 mt-2 mb-2">
                <div className="flex flex-row px-1 py-px sm:px-2 sm:py-2 bg-gray-200 rounded">
                    <h1 className="text-sm mx-1">Views: 500</h1>
                    <h1 className="text-sm">Likes: 1288</h1>
                </div>
                <h1 className="px-1 py-px sm:px-2 sm:py-2 bg-gray-200 rounded transition duration-300 hover:cursor-pointer hover:bg-gray-300">OPEN</h1>
            </div>
            
        </div>
    )
}

export default Post;