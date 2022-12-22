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
            <Container>
                <form onSubmit={handleSubmit}>
                    <input
                        type="text"
                        value={name}
                        placeholder="Name"
                        onChange={(e) => setName(e.target.value)}
                    />
                    <input
                        type="text"
                        value={description}
                        placeholder="Description"
                        onChange={(e) => setDescription(e.target.value)}
                    />
                    <input
                        type="file"
                        onChange={handleImage}
                    />
                    <button type="submit">Submitt item</button>
                </form>
                <img src={previewImage}></img>
                <p className="message">
                    {message}
                </p>
            </Container>
        );
    } else if (itemSubmitted === 'item submitted') {
        return (
            <Container>
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
                            onChange={(e) => setStopTime(e.target.value)}
                        />
                        <Button variant="contained" color="secondary" onClick={handleClick2}>Submit auction event</Button>
                    </Box>
                    <p className="message">
                        {message}
                    </p>
                </Paper>
            </Container>
        )
    };

}
