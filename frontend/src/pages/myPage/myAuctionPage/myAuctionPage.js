import { React, useState, useEffect } from "react";
import { Paper } from "@mui/material";
import ButtonAppBar from "../../../components/ButtonAppBar";

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

    let divStyle = {
        background: "#eee",
        padding: "20px",
        margin: "20px"  
    };

    useEffect(() => {
        fetch(`http://localhost:8080/auctionevent/user/${userId}/active/true`, {  
            method: "GET",
        })
        .then(res => res.json())
        .then((result) => {
            console.log(result);
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
        console.log(status);
    }

    return (
        <div>
            <ButtonAppBar />
            <div className="container">
                <h1>
                    {title? 'My active auctions' : 'My ended auctions'} 
                </h1>

                <div>
                    <button value={status? false : true} onClick={(ev) => HandleStatus(ev.target.value)}> 
                        {btnText? 'View ended auctions' : 'View active auctions'} 
                    </button>
                </div>

                <Paper elevation={6}> 
                    {AuctionEvents.map((auctionEvent) => {
                        return (
                            <Paper key={auctionEvent.id} style={divStyle}>
                                {auctionEvent.item.name}
                                <br /> 
                                Auction ends: {auctionEvent.stopTime} 
                                <br />
                                <div className="image-container">
                                    <img className="image" alt={"upploaded by the user"} src={`data:image/png;base64,${auctionEvent.item.image}`} />
                                </div>
                                {auctionEvent.item.description}
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