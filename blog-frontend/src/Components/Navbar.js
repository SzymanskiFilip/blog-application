function Navbar({status}){
    return(
        <nav className="
            bg-stone-900 
            h-14 
            text-white
            flex
            flex-row
            sm:grid
            sm:grid-cols-3
            sm:text-2xl
            items-center
            justify-center
            text-2xl
            ">
            <p></p>
            <p className="col-span-1 text-center mx-4">Blog It!</p>
            <div className="flex flex-row items-center justify-end">
                <p className="mx-4">Write</p>
                <p className="mx-4">{status ? "LOGOUT" : "LOGIN"}</p>
            </div>
        </nav>
    )
}

export default Navbar;