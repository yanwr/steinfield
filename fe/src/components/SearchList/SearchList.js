import React from 'react';
import * as Icon from 'react-bootstrap-icons';
import { InputGroup, FormControl, Button, Row, Col } from 'react-bootstrap';
import './style.css'

export default function SearchList(props) {
    const { onSearch } = props;
    return (
        <Row className="container-search">
            <Col>
                <InputGroup className="input-search"
                    onChange={value => onSearch(value.target.value)}
                >
                    <FormControl
                        placeholder="Busque um livro ..."
                        aria-label="Recipient's username"
                        aria-describedby="basic-addon2"
                    />
                    <InputGroup.Append>
                        <InputGroup.Text id="basic-addon2">
                            <Icon.Search size={20} />
                        </InputGroup.Text>
                    </InputGroup.Append>
                </InputGroup>
            </Col>
        </Row>
    );
}