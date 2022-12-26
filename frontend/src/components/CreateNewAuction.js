import { React, useState } from 'react'
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Container } from '@mui/system';
import { Paper, Button } from '@mui/material';
import axios from 'axios';

import "../css/CreateNewAuction.css";

export default function CreateNewAuction() {
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const [itemId, setItemId] = useState('');
    const [stopTime, setStopTime] = useState('');
    const [itemSubmitted, setItemSubmitted] = useState('');
    const [selectedImage, setSelectedImage] = useState('');
    const [previewImage, setPreviewImage] = useState('');
    const [message, SetMessage] = useState('');

    const userId = localStorage.getItem('userToken')

    const handleImage = (e) => {
        setSelectedImage(e.target.files[0])
        setPreviewImage(URL.createObjectURL(e.target.files[0]))
    };

    let handleSubmit = async (e) => {
        e.preventDefault()
        let formData = new FormData();
        formData.append("name", name)
        formData.append("description", description)
        formData.append("userId", userId)
        formData.append("file", selectedImage)
        const config = {
            headers: {
                'content-type': 'multipart/form-data',
                "Authorization": axios.defaults.headers.common["Authorization"]
            }
        }

        await axios.post("http://localhost:8080/item", formData, config, {
        }).then(response => {
            setItemId(response.data.id);
        });
        setItemSubmitted('item submitted')
        SetMessage('Item succesfully created')
    }

    const handleClick2 = (e) => {
        e.preventDefault()
        const auctionEvent = { "itemId": itemId, "stopTime": stopTime }
        fetch("http://localhost:8080/auctionevent?itemId=" + itemId, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": axios.defaults.headers.common["Authorization"],
            },
            body: JSON.stringify(auctionEvent)
        }).then(response => {
            if (response.ok) {
                response.json().then(json => {
                });
            }
        });
        setItemSubmitted('')
        SetMessage('Auction succesfully created')
        setPreviewImage('');
    }

    if (itemSubmitted === '') {
        return (
            <div className='createNewAuction-container'>
                <form onSubmit={handleSubmit}>
                    <input
                        className='item'
                        type="text"
                        value={name}
                        placeholder="Name of the item"
                        onChange={(e) => setName(e.target.value)}
                    /><br />
                    <textarea
                        className='item'
                        type="text"
                        cols="40"
                        rows="8"
                        value={description}
                        placeholder="Description of the item"
                        onChange={(e) => setDescription(e.target.value)}
                    /><br />
                    <input
                        className='item'
                        type="file"
                        onChange={handleImage}
                    /><br />
                    <button type="submit">Submitt item</button>
                </form>

                {previewImage && (
                    <div className="image-container">
                        <img className='image' src={previewImage} style={{ objectFit: 'contain' }}></img>
                    </div>
                )}

                <p className="message">
                    {message}
                </p>
            </div>
        );
    } else if (itemSubmitted === 'item submitted') {
        return (
            <div className='container'>
                <Paper elevation={3}>
                    <Box
                        component="form"
                        sx={{
                            '& > :not(style)': { m: 1, width: '25ch' },
                        }}
                        noValidate
                        autoComplete="off"
                    >

                        <TextField id="standard-basic" label="stoptime" variant="standard" fullWidth
                            value={stopTime}
                            placeholder="2024-12-24T14:00"
                            onChange={(e) => setStopTime(e.target.value)}
                        />
                        <Button variant="contained" color="secondary" onClick={handleClick2}>Submit auction event</Button>
                    </Box>
                    <p className="message">
                        {message}
                    </p>
                </Paper>
            </div>
        )
    };

}
