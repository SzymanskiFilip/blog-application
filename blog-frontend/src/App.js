import { useState } from "react";
import { Routes, Route } from "react-router-dom";
import LoginPage from "./Pages/LoginPage";
import { AuthContext } from "./Util/AuthContext";
import BlockAuth from "./Util/BlockAuth";
import HomePage from "./Pages/HomePage";
import PostPage from "./Pages/PostPage";

function App() {

  const [authenticated, setAuthenticated] = useState(false);

  const state = {
    authenticated,
    setAuthenticated
  };

  return (
    <Routes>
      
      <Route path="/login" element={
        <AuthContext.Provider value = {state}>
          <BlockAuth>
            <LoginPage />
          </BlockAuth>
        </AuthContext.Provider>
      }/>

      <Route path="/" element={
        <AuthContext.Provider value={state}>
          <HomePage />
        </AuthContext.Provider>
      }/>

      <Route path="/post/:id" element={
        <AuthContext.Provider value={state}>
          <PostPage />
        </AuthContext.Provider>
      }/>

    </Routes>
  );
}

export default App;
