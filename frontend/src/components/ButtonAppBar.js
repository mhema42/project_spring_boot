import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';


const token = localStorage.getItem("token");

export default function ButtonAppBar() {
    function logOut() {
        localStorage.removeItem("token");
        localStorage.removeItem("userToken")
        window.location.replace("/");
    }

    let button;
    if (token) {
        button = <Button color="inherit" onClick={logOut}>Log Out</Button>
    } else {
        button = <Button color="inherit" href='/login'>Login / Register</Button>
    }

    let menu;
    if (token) {
        menu =  <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                    <a style={{ color: 'white', textDecoration: 'none' }} href="/mypage">MyPage</a>
                </Typography>
    }

    return (
        <Box sx={{ flexGrow: 1 }}>
            <AppBar position="static">
                <Toolbar>
                    <Typography variant="h6" component="div" sx={{ flexGrow: 1 }} href="/">
                        <a style={{ color: 'white', textDecoration: 'none' }} href="/">TraderaMera</a>
                    </Typography>

                    {menu}                   
                    {button}

                </Toolbar>
            </AppBar>
        </Box>
    );
}