import React from "react";
import ButtonAppBar from "../../components/ButtonAppBar";
import CreateUser from "../../components/CreateUser";
import Login from "../../components/Login";
import "../../css/registerPage.css"
import LoginPage from "../loginpage/loginPage";

const RegisterPage = () => {
    return (
        <div>
            <ButtonAppBar />
                <div className="container">
                <h1>
                    Create you account!
                </h1>
                <CreateUser />               
            </div>
            <div className="backtologin">
            Already a member?
            <a href="/login"> Login</a>
            </div>
        </div>
    );
};

export default RegisterPage;