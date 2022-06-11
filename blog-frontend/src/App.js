import { useState } from "react";
import { Routes, Route } from "react-router-dom";
import LoginPage from "./Pages/LoginPage";
import { AuthContext } from "./Util/AuthContext";
import BlockAuth from "./Util/BlockAuth";
import HomePage from "./Pages/HomePage";
import PostPage from "./Pages/PostPage";
import { useEffect } from "react";
import { checkAuthentication } from "./Util/checkAuthentication";

function App() {

  const [authenticated, setAuthenticated] = useState(false);

  const state = {
    authenticated,
    setAuthenticated
  };

  function checkStatus(setAuthenticated){
    checkAuthentication(state.setAuthenticated);
  }

  useEffect(() => {
    
  }, []);

  return (
    <Routes>
      
      <Route path="/login" element={
        <AuthContext.Provider value = {state}>
          <BlockAuth>
            <LoginPage checkStatus={checkStatus}/>
          </BlockAuth>
        </AuthContext.Provider>
      }/>

      <Route path="/" element={
        <AuthContext.Provider value={state}>
          <HomePage checkStatus={checkStatus}/>
        </AuthContext.Provider>
      }/>

      <Route path="/post/:id" element={
        <AuthContext.Provider value={state}>
          <PostPage checkStatus={checkStatus}/>
        </AuthContext.Provider>
      }/>

    </Routes>
  );
}

export default App;
