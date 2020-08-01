import React from 'react';
import { Spinner } from 'react-bootstrap';
import './style.css';

export default function Loading(props) {
    const { txt, style } = props
    return(
        <>
            <Spinner 
                className="loading-container"
                animation="border" 
                variant="dark" 
                style={style}
            /> 
            {txt}
        </>
    );
}