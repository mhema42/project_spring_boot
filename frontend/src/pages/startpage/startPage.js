import React, { useEffect, useState } from "react";
import ButtonAppBar from "../../components/ButtonAppBar";
import axios from 'axios';

import "../../css/startPage.css";

const StartPage = () => {
    const [auctions, setAuctions] = useState([])
    const [bid, setBid] = useState('')
    const [auctionEventId, setAuctionEventId] = useState(null)
    const [userId] = useState(() => {
        const saved = localStorage.getItem("userToken");
        const initialValue = JSON.parse(saved);
        return initialValue || "";
    });
    const [isDisabled, setIsDisabled] = useState(true);
    const [checked, setChecked] = useState(false);

    const canBeSubmitted = () => {
        return checked ? setIsDisabled(true) : setIsDisabled(false);
    };

    const onCheckboxClick = () => {
        setChecked(!checked);
        return canBeSubmitted();
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

    const handleSubmit = (e) => {
        e.preventDefault()
        const offer = { "offer": bid}
        console.log(offer)
        fetch(`http://localhost:8080/bid?auctioneventId=${auctionEventId}&bidderId=${userId}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": axios.defaults.headers.common["Authorization"]
            },
            body: JSON.stringify(offer)
        }).then(async () => {
            window.location.replace("/");
        })
    }

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
                            <span> Top bid: ${Intl.NumberFormat("eur").format(auction.highestBid)} </span>
                            <span> Ends: {auction.stopTime.toString().substring(2, 10) + " kl." + auction.stopTime.toString().substring(11, 16)} </span>
                        </div>

                        <div className="partial-2">
                            <img className="image" alt={"upploaded by the user"} src={`data:image/png;base64,${auction.item.image}`} />
                            <div className="info_and_bid">
                                {auction.item.description}
                                <form className="form" onSubmit={handleSubmit}>
                                    <input className="label" name="bid" type="text" value={bid} maxlength = "10" onChange={(e) => setBid(e.target.value)} required="required"/>
                                    <input type="radio"  value={auction.id} onChange={(e) => setAuctionEventId(e.target.value)} required="required" onClick={onCheckboxClick}/>
                                    Confirm bid!
                                    <button disabled={isDisabled} type="submit"> Submitt bid </button>
                                </form>
                            </div>                          
                        </div>
                     
                        {auction.bids.sort((a, b) => a.id - b.id).map((bid) => {
                            return (
                                <div className="bid-content" key={bid.id}>
                                    <span className="bidder"> Bidder: {bid.bidder.username} </span>
                                    <span className="bid"> Bid: ${Intl.NumberFormat("eur").format(bid.offer)} </span>  
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
