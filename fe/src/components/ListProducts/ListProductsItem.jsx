import React from 'react'
import * as Icon from 'react-bootstrap-icons';
import './style.css'
import { Button } from 'react-bootstrap'

export default function ListProductsItem(props) {

    const { item } = props
    return (
        <div className="container">
                <div className="Card">
                    <div className="imgProduto"><img src={item.imgUrl} alt="" /></div>
                    <div className="Title" >{item.name}</div>
                    <div className="Subtitle">{item.description}</div>
                    <div className="Price">R${item.price}</div>
                    <div className="line"></div>
                    <div className="btnsCard">
                        <Button className="btnAddCart" variant="light"><Icon.Cart3 size="20" /></Button>
                        <Button className="btnSaibaMais" variant="light">See More</Button>
                    </div>
                </div>
        </div>
    );
}