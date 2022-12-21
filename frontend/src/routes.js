import React from "react";
import { Redirect, Switch, Route, Router } from "react-router-dom";
import RouteSecurity from "./components/RouteSecurity";

//history
import { history } from './helpers/history';

//pages
import LoginPage from "./pages/loginpage/loginPage";
import Mypage from "./pages/myPage/myPage";
import NewAuctionPage from "./pages/myPage/newAuctionPage/newAuctionPage";
import StartPage from "./pages/startpage/startPage";
import RegisterPage from "./pages/registerpage/registerPage";

function Routes() {
    return (
        <Router history={history}>
            <Switch>
                <Route
                    exact
                    path="/"
                    component={StartPage}
                />
                <RouteSecurity
                    exact
                    path="/mypage"
                    component={Mypage}
                />
                <RouteSecurity
                    exact
                    path="/newauction"
                    component={NewAuctionPage}
                />
                <Route
                    path="/login"
                    component={LoginPage}
                />
                <Route
                    exact                    
                    path="/registerpage"
                    component={RegisterPage}
                />
                <Redirect to="/mypage" />
            </Switch>
        </Router>
    );
}

export default Routes