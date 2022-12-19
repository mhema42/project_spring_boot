import { React, useState } from 'react'
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Container } from '@mui/system';
import { Paper, Button } from '@mui/material';
import { setTokenForAuthentication } from '../helpers/setTokenForAuthentication';

export default function Login() {
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [userId, setUserId] = useState('')


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
        }).then(res => {
            if (res.status === 200) {
                console.log("logged in")
                const token = res.headers.get("Authorization")
                localStorage.setItem("token", token)
                setTokenForAuthentication(token);
                window.location.replace("/");
            } //else error message
        })
    }

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

        </Container >
    );
}
