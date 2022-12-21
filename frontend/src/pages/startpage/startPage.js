import { Paper } from "@mui/material";
import React, { useEffect, useState } from "react";
import ButtonAppBar from "../../components/ButtonAppBar";


const StartPage = () => {
    const [auctions, setAuctions] = useState([])
    const [bids, setBids] = useState([])

    var divStyle = {
        background: "#eee",
        padding: "20px",
        margin: "20px"
    };

    useEffect(() => {
        fetch("http://localhost:8080/auctionevent", {
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
                    </Paper>
                ))
                }
                
                {bids.map(bids => (
                    <Paper key={bids.id} style={divStyle}>
                        AuctionId: {bids.auctionEvent.id} <br />
                        Item: {bids.auctionEvent.item.name} <br />
                        Description: {bids.auctionEvent.item.description} <br />
                        Stoptime: {bids.auctionEvent.stopTime} <br />
                        Bid: {bids.offer} <br />
                    </Paper>
                ))
                }
            </Paper>
        </div>
    );
};

export default StartPage;





