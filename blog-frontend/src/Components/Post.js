import {Link} from 'react-router-dom';

function Post({data}){

    let shorterBody = data.body.slice(0, 200);
    shorterBody += "...";

    return(
        <div className="w-8/12 flex flex-col justify-between bg-gray-100 px-2 py-2 rounded mb-12">
            <div>
                <img src={data.image_name} alt={data.image_name}
                className="post-bg-img rounded"
            />
            </div>
            
            <div className="mx-4">
                <h1 className="text-2xl sm:text-5xl mt-2 mb-2">{data.title}</h1>
                <p>{shorterBody} <Link to={`/post/${data.id}`} className="text-blue-500">read more</Link></p>

            </div>

            <div className="flex flex-row items-center justify-between mx-4 mt-2 mb-2">
                <div className="flex flex-row px-1 py-px sm:px-2 sm:py-2 bg-gray-200 rounded">
                    <h1 className="text-sm">Likes: {data.likes}</h1>
                </div>
                <h1 className="px-1 py-px sm:px-2 sm:py-2 bg-gray-200 rounded transition duration-300 hover:cursor-pointer hover:bg-gray-300">OPEN</h1>
            </div>
            
        </div>
    )
}

export default Post;