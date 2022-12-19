import axios from 'axios';

export const setTokenForAuthentication = token => {
    if (token) {
        axios.defaults.headers.common["Authorization"] = `${token}`;
    }
    else
        delete axios.defaults.headers.common["Authorization"];
}