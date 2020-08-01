import React from 'react';
import { Navbar, Nav } from 'react-bootstrap';
import './style.css';

export default function FooterComponent(props) {
    return(
        
        <Navbar 
            expand="lg"
            className="justify-content-center"
            fixed="bottom"
        >
            <Navbar.Brand className="text">
            <img
                    alt="Steinfield"
                    src="https://64.media.tumblr.com/2eac93b1e593e0cdf9a3844abf628822/ed5870fd3cc2f017-c2/s500x750/ccb25188de146b07e3910d1f88039180078ad3b6.png"
                    width="30"
                    height="30"
                    className="d-inline-block align-bottom"
                />
              @Copyright Steinfield 
                <img
                    alt="Steinfield"
                    src="https://64.media.tumblr.com/2eac93b1e593e0cdf9a3844abf628822/ed5870fd3cc2f017-c2/s500x750/ccb25188de146b07e3910d1f88039180078ad3b6.png"
                    width="30"
                    height="30"
                    className="d-inline-block align-bottom"
                />
                </Navbar.Brand>
        </Navbar>
    );
}