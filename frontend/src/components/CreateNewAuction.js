import { React, useState } from 'react'
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Container } from '@mui/system';
import { Paper, Button } from '@mui/material';
import axios from 'axios';

export default function CreateNewAuction() {
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const [itemId, setItemId] = useState('');
    const [stopTime, setStopTime] = useState('');
    const [itemSubmitted, setItemSubmitted] = useState('');
    const [selectedImage, setSelectedImage] = useState('');

    const userId = localStorage.getItem('userToken')

    const handleImage = (e) => {
        setSelectedImage(e.target.files[0])
    }

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
            if (response.ok) {
                response.json().then(json => {
                    setItemId(json.id);
                    console.log(itemId);
                });
            }
        });
        setItemSubmitted('item submitted')
    }

    const handleClick2 = (e) => {
        e.preventDefault()
        const auctionEvent = { "itemId": itemId, "stopTime": stopTime }
        console.log(auctionEvent)
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
                    console.log(json);
                });
            }
        });
        setItemSubmitted('')
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
                </Paper>
            </Container>
        )
    };

}
