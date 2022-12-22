import { React, useState, useEffect } from "react";
import { Paper } from "@mui/material";
import ButtonAppBar from "../../../components/ButtonAppBar";

const ActiveAuctionPage = () => {
    const [AuctionEvents, setAuctionEvents] = useState([]);
    const [userId, setUserId] = useState(1);

    let divStyle = {
        background: "#eee",
        padding: "20px",
        margin: "20px"
    };

    useEffect(() => {
        fetch("http://localhost:8080/auctionevent/user/"+ userId +"/active/true", {
            method: "GET",
        })
        .then(res => res.json())
        .then((result) => {
            console.log(result);
            setAuctionEvents(result);
        })
    }, [])

    return (
        <div>
            <ButtonAppBar />
            <div className="container">
                <h1>
                    My active auctions
                </h1>
                <Paper elevation={6}> 
                    {AuctionEvents.map((auctionEvent) => {
                        return (
                            <Paper key={auctionEvent.id} style={divStyle}>
                                {auctionEvent.item.name}
                                <br /> 
                                {auctionEvent.item.description}
                                <br />
                                Auction ends: {auctionEvent.stopTime} 
                                <br /> 
                                {auctionEvent.bids.map((bid) => {
                                    return (
                                        <Paper key={bid.id} style={divStyle}>
                                            Bidder: {bid.bidder.username}
                                            <br />
                                            Bid: {bid.offer}       
                                        </Paper>
                                    );
                                })}  
                            </Paper>
                        );
                    })} 
                </Paper>
            </div>  
        </div>
    );
};

export default ActiveAuctionPage;