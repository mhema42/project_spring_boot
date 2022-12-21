import React from "react";
import ButtonAppBar from "../../components/ButtonAppBar";
import Login from "../../components/Login";
import RegisterPage from "../registerpage/registerPage";
import "../../css/login.css"

const LoginPage = () => {
    return (
        <div>
            <ButtonAppBar />
            <div className="text">
            <h1>
                This is the loginpage
            </h1>
            </div>
            <Login />
            <div className="register">Not a member?
                <div className="link"></div>
               <a href="./registerpage"> Register here </a>
               </div>
            
        </div>
        
        
        
    );
};

export default LoginPage;