import React, { useEffect, useState } from "react";
import ButtonAppBar from "../../components/ButtonAppBar";
import axios from 'axios';

import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';

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

    const [open, setOpen] = React.useState(false);
    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    const style = {
        position: 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: 400,
        bgcolor: 'background.paper',
        border: '2px solid #000',
        boxShadow: 24,
        p: 4,
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

    const handleSubmit = async (e) => {
        e.preventDefault()

        const offer = { "offer": bid}
        console.log(offer)
        await fetch(`http://localhost:8080/bid?auctioneventId=${auctionEventId}&bidderId=${userId}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": axios.defaults.headers.common["Authorization"]
            },
            body: JSON.stringify(offer)
        }).then(async () => {
            window.location.replace("/");
        })
    };

    // toggle bid button if token (logged in)
    const token = localStorage.getItem("token");
    let display = false
    if(token) {display = true}

    return (
        <div>
            <ButtonAppBar />
            <div className="startPage-container">
                <h1 className="title">    
                    All active auctions
                </h1>

                <Modal
                    open={open}
                    onClose={handleClose}
                    aria-labelledby="modal-modal-title"
                    aria-describedby="modal-modal-description"
                    >
                <Box sx={style}>
                    <Typography id="modal-modal-title" variant="h6" component="h2">
                        Add your bid
                    </Typography>
                    <br />

                    <form onSubmit={handleSubmit}>
                        <input name="bid" type="text" maxlength="10" value={bid} onChange={(e) => setBid(e.target.value)} required="required"/>
            
                        <button type="submit"> Submit bid </button>
                    </form>

                </Box>
                </Modal>

                {auctions.map(auction => (
                    <div className="auction-content" key={auction.id}>
            
                        <div className="partial-1">
                            <span className="name"> {auction.item.name} </span> 
                            <span> Top bid: ${Intl.NumberFormat("eur").format(auction.highestBid)} </span>
                            <span> Ends: {auction.stopTime.toString().substring(2, 10) + " kl." + auction.stopTime.toString().substring(11, 16)} </span>
                        </div>

                        <div className="partial-2">
                            <div className="image-container">
                                <img className="image" alt={"upploaded by the user"} src={`data:image/png;base64,${auction.item.image}`} />
                            </div>                    

                            <div className="auction-info">
                                <div className="description"> {auction.item.description} </div>

                                {display && 
                                    <div className="button">
                                        <Button value={auction.id} onClick={(e) => {    
                                            setAuctionEventId(e.target.value);
                                            handleOpen()}}> 
                                            Add your bid   
                                        </Button>
                                    </div>
                                }
                            </div>
                        </div>
                        
                        <div className="show-bid">
                            <span className="bidBtn">Show all bids</span>

                            <div className="show-bids">         
                                {auction.bids.sort((a, b) => a.id - b.id).map((bid) => {
                                    return (
                                        <div className="bid-content" key={bid.id}>
                                            <span className="bidder"> Bidder: {bid.bidder.username} </span>
                                            <span className="bidder"> {bid.bidTime.toString().substring(2, 10) + " kl." + bid.bidTime.toString().substring(11, 16)} </span>
                                            <span className="bid"> Bid: ${Intl.NumberFormat("eur").format(bid.offer)} </span>  
                                        </div>
                                    )
                                })}
                            </div>
                        </div>     

                    </div>
                ))}        
            </div>
        </div>
    );
};

export default StartPage;