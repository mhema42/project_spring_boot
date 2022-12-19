import React from "react";
import ButtonAppBar from "../../components/ButtonAppBar";
import "../../css/myPage.css";

const MyPage = () => {
    return (
        <div>
            <ButtonAppBar />
            <div className="container">
                <h1>
                    This is my page
                </h1>
                <a href="/newauction">New auction</a>
                <a href="/">My active auctions</a>
                <a href="/">My ended auctions</a>
            </div>
        </div>
    );
};

export default MyPage;