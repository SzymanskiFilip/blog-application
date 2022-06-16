function Spinner({spinnerText}){
    return(
        <div>
            <div className="loading"></div>
            <p className="text-center">{spinnerText}</p>
        </div>
    )
}

export default Spinner;