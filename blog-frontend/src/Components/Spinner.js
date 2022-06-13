function Spinner({spinnerText}){
    return(
        <div>
            <div className="loading"></div>
            {spinnerText}
        </div>
    )
}

export default Spinner;