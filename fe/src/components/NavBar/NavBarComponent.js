import React from 'react';
import { Navbar, Nav } from 'react-bootstrap';
import Logo from '../../img/logo.png';
import './style.css'

export default function NavBarComponent(props) {
    return(
        <Navbar 
            collapseOnSelect  
            expand="lg"
            fixed="top"
            className="navbar-container"
        >
            <Navbar.Brand href="/">
                <img
                    alt="Steinfield"
                    src={Logo}
                    width="30"
                    height="30"
                    className="d-inline-block align-top"
                />{' '}
                Steinfield
            </Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav" />
            <Navbar.Collapse id="basic-navbar-nav" className="justify-content-end">
                <Nav className="navbar-container-btn">
                   {props.children}
                </Nav>
            </Navbar.Collapse>
        </Navbar>
    );
}