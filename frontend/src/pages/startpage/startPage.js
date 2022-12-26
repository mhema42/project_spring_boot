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
            <div className="startPage-container">
                <h1 className="title">    
                    All active auctions
                </h1>

                {auctions.map(auction => (
                    <div className="auction-content" key={auction.id}>
            
                        <div className="partial-1">
                            <span className="name"> {auction.item.name} </span> 
                            <span> HighestBid: {auction.highestBid} </span>
                            <span> {auction.stopTime.toString().substring(0, 10) + " kl." + auction.stopTime.toString().substring(11, 16)} </span>
                        </div>

                        <div className="partial-2">
                            <div className="image-container">
                                <img className="image" alt={"upploaded by the user"} src={`data:image/png;base64,${auction.item.image}`} />
                            </div>

                            <span className="description"> {auction.item.description} </span>

                        </div>
                        
                     
                        {auction.bids.map((bid) => {
                                return (
                                    <div className="bid-content" key={bid.id}>
                                        <span className="bidder"> Bidder: {bid.bidder.username} </span>
                                        <span className="bid"> Bid: {bid.offer} </span>  
                                    </div>
                                )
                            })}       
                    </div>
                ))}        
            </div>
        </div>
    );
};

export default StartPage;
