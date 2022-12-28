import { React, useState, useEffect } from "react";
import { Paper } from "@mui/material";
import ButtonAppBar from "../../../components/ButtonAppBar";
import "../../../css/myAuctionPage.css";

const ActiveAuctionPage = () => {
    const [AuctionEvents, setAuctionEvents] = useState([]);
    const [status, setStatus] = useState(false);
    const [btnText, setBtnText] = useState("View endend auctions");
    const [title, setTitle] = useState("My active auctions");
    const [userId] = useState(() => {
        const saved = localStorage.getItem("userToken");
        const initialValue = JSON.parse(saved);
        return initialValue || "";
    });

    useEffect(() => {
        fetch(`http://localhost:8080/auctionevent/user/${userId}/active/true`, {  
            method: "GET",
        })
        .then(res => res.json())
        .then((result) => {
            setAuctionEvents(result);
        })
    }, [])

    async function HandleStatus() { 
        const res = await fetch(`http://localhost:8080/auctionevent/user/${userId}/active/${status}`,
        {
            method: "GET",
        })  
        const result = await res.json();  
        setAuctionEvents(result);
        setStatus(current => !current);
        setBtnText(current => !current);
        setTitle(current => !current);
    }

    return (
        <div>
            <ButtonAppBar />
            <div className="myAuctionPage-container">
                <h1 className="title">    
                    {title? 'My active auctions' : 'My ended auctions'} 
                </h1>
                <button className="toggleBtn" value={status? false : true} onClick={(ev) => HandleStatus(ev.target.value)}> 
                    {btnText? 'View ended auctions' : 'View active auctions'} 
                </button>
       
                {AuctionEvents.map((auctionEvent) => {
                    return (
                        <div className="auction-content" key={auctionEvent.id}>
                            <div className="partial-1">
                                <span className="name"> {auctionEvent.item.name} </span> 
                                <span> Top bid: ${Intl.NumberFormat('eur').format(auctionEvent.highestBid)} </span>
                                <span> Ends: {auctionEvent.stopTime.toString().substring(2, 10) + " kl." + auctionEvent.stopTime.toString().substring(11, 16)} </span>
                            </div>
                            <div className="partial-2">
                                <div>
                                    <img className="image" alt={"upploaded by the user"} src={`data:image/png;base64,${auctionEvent.item.image}`} />
                                </div>

                                <span className="description"> {auctionEvent.item.description} </span>
                            </div>
            
                            {auctionEvent.bids.map((bid) => {
                                return (
                                    <div className="bid-content" key={bid.id}>
                                        <span className="bidder"> Bidder: {bid.bidder.username} </span>
                                        <span className="bidder"> {bid.bidTime.toString().substring(2, 10) + " kl." + bid.bidTime.toString().substring(11, 16)} </span>
                                        <span className="bid"> ${Intl.NumberFormat('eur').format(bid.offer)}</span>  
                                    </div>
                                );
                            })}  
                        </div>
                    );
                })} 
            </div>
        </div>     
    );
};

export default ActiveAuctionPage;