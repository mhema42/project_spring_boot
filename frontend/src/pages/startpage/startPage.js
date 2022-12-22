import { Paper } from "@mui/material";
import React, { useEffect, useState } from "react";
import ButtonAppBar from "../../components/ButtonAppBar";
import "../../css/startPage.css";

const StartPage = () => {
    const [auctions, setAuctions] = useState([])
    const [bids, setBids] = useState([])

    var divStyle = {
        background: "#eee",
        padding: "20px",
        margin: "20px",
        width: "500px",
    };

    useEffect(() => {
        fetch("http://localhost:8080/auctionevent/active/true", {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            }
        })
            .then(res => res.json())
            .then((result) => {
                setAuctions(result);
            }
            )
    }, [])

    useEffect(() => {
    fetch("http://localhost:8080/bid", {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
        }
    })
        .then(res => res.json())
        .then((result) => {
            setBids(result);

        }
        )
    }, [])

    return (
        <div>
            <ButtonAppBar />
            <h1>
                This is the startpage for auctions
            </h1>
            <Paper elevation={6}>

                {auctions.map(auction => (
                    <Paper key={auction.id} style={divStyle}>
                        AuctionId: {auction.id} <br />
                        Item: {auction.item.name} <br />
                        Description: {auction.item.description} <br />
                        Stoptime: {auction.stopTime} <br />

                        <div className="image-container">
                            <img className="image" alt={"upploaded by the user"} src={`data:image/png;base64,${auction.image}`} />
                        </div>

                        {bids.map(bids => (
                            <Paper key={bids.id} style={divStyle}>
                                Id: {bids.id} <br />
                                Bid: {bids.offer} <br />
                                Name: {bids.bidder.username}
                            </Paper>
                        ))
                        } 
                    </Paper>
                ))
                }       

            </Paper>
        </div>
    );
};

export default StartPage;

