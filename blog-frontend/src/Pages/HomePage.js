import { useContext } from "react";
import Navbar from "../Components/Navbar";
import { AuthContext } from "../Util/AuthContext";

function HomePage(){

    const context = useContext(AuthContext);

    return(
        <div>
            <Navbar status={context}/>
        </div>
    )
}

export default HomePage;