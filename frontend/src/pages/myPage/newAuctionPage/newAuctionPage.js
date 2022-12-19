import React from "react";
import ButtonAppBar from "../../../components/ButtonAppBar";
import CreateNewAuction from "../../../components/CreateNewAuction";

const NewAuctionPage = () => {
    return (
        <div>
            <ButtonAppBar />
            <div className="container">
                <h1>
                    Create new auction
                </h1>
                <CreateNewAuction />
            </div>
        </div>
    );
};

export default NewAuctionPage;