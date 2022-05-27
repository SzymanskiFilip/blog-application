function Navbar(){
    return(
        <nav className="
            bg-stone-900 
            h-14 
            text-white
            grid
            grid-cols-3
            items-center
            justify-center
            text-2xl
            ">
            <p></p>
            <p className="col-span-1 text-center">Blog It!</p>
            <div className="flex flex-row items-center justify-end">
                <p className="mx-4">Write</p>
                <p className="mx-4">Login</p>
            </div>
        </nav>
    )
}

export default Navbar;