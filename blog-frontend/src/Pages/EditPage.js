import {useLocation} from "react-router-dom";

function EditPage(){

    const location = useLocation();
    const data = location.state;
    
    console.log(data)
    return(
        <h1>{data.id}</h1>
    )
}

export default EditPage;