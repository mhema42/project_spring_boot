import React from "react";
import ButtonAppBar from "../../components/ButtonAppBar";
import Login from "../../components/Login";

const LoginPage = () => {
    return (
        <div>
            <ButtonAppBar />
            <h1>
                This is the loginpage
            </h1>
            <Login />
        </div>
    );
};

export default LoginPage;