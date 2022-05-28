function Post({data}){
    return(
        <div className="w-8/12 drop-shadow-xl">
            <img src={data.image} alt="post banner"
                className="post-bg-img"
            />
            <h1 className="text-5xl">{data.title}</h1>
        </div>
    )
}

export default Post;