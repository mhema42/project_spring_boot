import { React, useState } from 'react'
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Container } from '@mui/system';
import { Paper, Button } from '@mui/material';
import { setTokenForAuthentication } from '../helpers/setTokenForAuthentication';
import axios from 'axios';

export default function Login() {
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')


    const handleClick = async (e) => {
        e.preventDefault()
        const user = { "username": username, "password": password }
        await axios.post("http://localhost:8080/authenticate", user)
            .then(res => {
                if (res.status === 200) {
                    const token = res.headers.get("Authorization")
                    localStorage.setItem("token", token)
                    setTokenForAuthentication(token);
                }
            })
        await axios.get("http://localhost:8080/user/username?username=" + username)
            .then(res => {
                if (res.status === 200) {
                    const token = res.data;
                    localStorage.setItem("userToken", token)
                    window.location.replace("/mypage");
                }
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
