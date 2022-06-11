export async function checkAuthentication(setAuthenticated){
    await fetch("http://localhost:8080/authenticated", {
      method: "POST",
      mode: "cors",
      credentials: "include"
    }).then(res => res.json())
    .then(res => setAuthenticated(res))
}