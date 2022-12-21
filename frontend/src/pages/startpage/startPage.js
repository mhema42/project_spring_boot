import { Paper } from "@mui/material";
import React, { useEffect, useState } from "react";
import ButtonAppBar from "../../components/ButtonAppBar";
import "../../css/start.css"


const StartPage = () => {
    const [items, setItems] = useState([])

    var divStyle = {
        background: "#eee",
        padding: "20px",
        margin: "20px"
    };

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
        <><div className="button">
        <ButtonAppBar />
        </div>
        <div className="start">                
                <h1>
                    This is the startpage
                </h1>
                <h1>Items</h1>
                <Paper elevation={6}>

                    {items.map(item => (
                        <Paper key={item.id} style={divStyle}>
                            name: {item.name} <br />
                            description: {item.description} <br />
                        </Paper>
                    ))}

                </Paper>
            </div></>
    );
};

export default StartPage;