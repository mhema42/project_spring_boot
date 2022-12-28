import { React, useState } from 'react'
import axios from 'axios';


export default function CreateBid() {
    const [setBid] = useState('')
    const [auctionEventId] = useState('')
    const [userId] = useState(() => {
        const saved = localStorage.getItem("userToken");
        const initialValue = JSON.parse(saved);
        return initialValue || "";
    });

    const handleSubmit = (e) => {
        e.preventDefault()
        setBid(e)
        fetch(`http://localhost:8080/bid?auctioneventId=${auctionEventId}&bidderId=${userId}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": axios.defaults.headers.common["Authorization"]
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


