import { React, useState } from 'react'
import { setTokenForAuthentication } from '../helpers/setTokenForAuthentication';


export default function CreateBid() {
    const [bid, setBid] = useState('')
    const [auctionEventId, setAuctionEventId] = useState('')
    const [userId] = useState(() => {
        const saved = localStorage.getItem("userToken");
        const initialValue = JSON.parse(saved);
        return initialValue || "";
    });

    const handleSubmit = (e) => {
        e.preventDefault()
        setBid(e)
        //const s = { "bid": bid, "userId": userId, "auctionEventID": auctionEventId }
        console.log(bid)
        fetch(`http://localhost:8080/bid?auctioneventId=${auctionEventId}&bidderId=${userId}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify()
        }).then(async () => {

        })
    }

    return (
        <div>
            <form>
                <label>
                    Bid:
                    <input type="text" name="bid" />
                </label>
                <input type="submit" value="Submit" onChange={(ev) => handleSubmit(ev.target.value)} />
            </form>
        </div>
    );
}


