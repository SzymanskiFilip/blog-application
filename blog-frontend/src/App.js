import { useState } from "react";
import { Routes, Route } from "react-router-dom";
import LoginPage from "./Pages/LoginPage";
import { AuthContext } from "./Util/AuthContext";

function App() {

  const [authenticated, setAuthenticated] = useState(false);

  return (
    <Routes>
      
      <Route path="/login" element={
        <AuthContext.Provider value = {authenticated}>
          <LoginPage/>
        </AuthContext.Provider>
      }/>

    </Routes>
  );
}

export default App;
