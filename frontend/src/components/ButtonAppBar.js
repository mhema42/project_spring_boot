import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import IconButton from '@mui/material/IconButton';

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

    return (
        <Box sx={{ flexGrow: 1 }}>
            <AppBar position="static">
                <Toolbar>
                    <IconButton
                        size="large"
                        edge="start"
                        color="inherit"
                        aria-label="menu"
                        sx={{ mr: 2 }}
                    >
                    </IconButton>
                    <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                        ❚█══ Tradera 2.0 ══█❚
                    </Typography>
                    <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                        <a href='/mypage'>MyPage</a>
                    </Typography>
                    {button}

                </Toolbar>
            </AppBar>
        </Box>
    );
}
