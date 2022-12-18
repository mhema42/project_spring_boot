import { React, useEffect, useState } from 'react'
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Container } from '@mui/system';
import { Paper, Button } from '@mui/material';

export default function Login() {
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [items, setItems] = useState([])

    var divStyle = {
        background: "#eee",
        padding: "20px",
        margin: "20px"
    };

    const handleClick = (e) => {
        e.preventDefault()
        const user = { "username": username, "password": password }
        console.log(user)
        fetch("http://localhost:8080/authenticate", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(user)
        }).then(() => {
            console.log("logged in")
        })
    }

    useEffect(() => {
        fetch("http://localhost:8080/item", {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            }
        })
            .then(res => res.json())
            .then((result) => {
                setItems(result);
            }
            )
    }, [])

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
                    <TextField id="standard-basic" label="Username" variant="standard" fullWidth
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                    />
                    <TextField id="standard-basic" label="Password" variant="standard" fullWidth
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                    <Button variant="contained" color="secondary" onClick={handleClick}>Submit</Button>
                </Box>
            </Paper>
            <h1>Items</h1>
            <Paper elevation={6}>

                {items.map(item => (
                    <Paper key={item.id} style={divStyle}>
                        name: {item.name} <br />
                        description: {item.description} <br />
                    </Paper>
                ))
                }

            </Paper>
        </Container >
    );
}
