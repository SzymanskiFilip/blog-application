import { useContext, useState } from "react";
import Navbar from "../Components/Navbar";
import { AuthContext } from "../Util/AuthContext";

function AccountPage(){

    const context = useContext(AuthContext);

    const [optionNumber, setOptionNumber] = useState(0);
    const options = [{name: "Account", index: 0}, {name: "Posts", index: 1}];


    return(
        <div>
            <Navbar status={context}/>
            <div className="flex flex-col items-center mt-2">
                <div className="flex flex-row items-center justify-center bg-gray-200 rounded text-center">
                    {
                        options.map((option) => {
                            if(option.index === optionNumber){
                                return <h1 className={"p-1 rounded w-48 bg-gray-500 hover:cursor-pointer"}
                                onClick={() => setOptionNumber(option.index)}
                                >{option.name}</h1>
                            } else {
                                return <h1 className={"p-1 rounded w-48 bg-gray-300 hover:cursor-pointer"}
                                onClick={() => setOptionNumber(option.index)}
                                >{option.name}</h1>
                            }
                        })
                    }
                </div>
            </div>
        </div>
    )
}

export default AccountPage;