function LoginPage(){
    return(
        <div>
            <nav className="
            bg-stone-900 
            h-14 
            text-white
            flex flex-row
            justify-center
            items-center
            text-2xl
            ">
            Blog It!
            </nav>

            <div className="absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2">
                <div className="
                flex flex-col
                bg-white
                border-2
                border-black
                rounded
                items-center
                justify-center
                px-4
                py-4
                ">
                <p className="text-center text-black text-2xl">Login</p>
                {/*TODO: ADD INPUT STYLING TO CUSTOM CLASS! */}
                <input type="text" className="outline-none border-black border px-2" placeholder="Username"/>
                <input type="password" className="outline-none border-black border px-2 my-2" placeholder="Password"/>
                <button className="border-black border px-px rounded">Login</button>
                <p className="text-center text-xs mt-2">Don't have an account?</p>
                <p className="text-center text-xs hover:cursor-pointer hover:text-blue-500">Create one here</p>
                </div>
            </div>
        </div>
    )
}

export default LoginPage;