import React from 'react';
import { Nav} from 'react-bootstrap';

export default function ButtonLogin(props) {
    const { title, to, toDo } = props;
   return(
        <Nav.Link 
            href={to}
            onClick={toDo}
        >
            {title}
        </Nav.Link>
   ); 
}