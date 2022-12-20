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

    const userId = localStorage.getItem('userToken')

    const handleClick1 = async (e) => {
        e.preventDefault()
        const item = { "name": name, "description": description }
        console.log(item)
        await fetch("http://localhost:8080/item", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": axios.defaults.headers.common["Authorization"],
            },
            body: JSON.stringify(item)
        }).then(response => {
            if (response.ok) {
                response.json().then(json => {
                    fetch("http://localhost:8080/item/addowner?userId=" + userId + "&itemId=" + json.id, {
                        method: "PATCH",
                        headers: {
                            "Content-Type": "application/json",
                            "Authorization": axios.defaults.headers.common["Authorization"],
                        },
                        body: JSON.stringify(item)
                    }).then(response => {
                        if (response.ok) {
                            response.json().then(json => {
                                setItemId(json.id);
                                console.log(itemId);
                            });
                        }
                    });
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
                <Paper elevation={3}>
                    <Box
                        component="form"
                        sx={{
                            '& > :not(style)': { m: 1, width: '25ch' },
                        }}
                        noValidate
                        autoComplete="off"
                    >

                        <TextField id="standard-basic" label="Name" variant="standard" fullWidth
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                        />
                        <TextField id="standard-basic" label="Description" variant="standard" fullWidth
                            value={description}
                            onChange={(e) => setDescription(e.target.value)}
                        />
                        <Button variant="contained" color="secondary" onClick={handleClick1}>Submit item</Button>
                    </Box>
                </Paper>
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
