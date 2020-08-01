import React from 'react'
import { ListGroup } from 'react-bootstrap'
import ListProductsItem from './ListProductsItem'

export default function ListProducts(props) {
    const {data} = props;
    return (
        <ListGroup>
            { data.map((product) =>{
                return(
                    <ListProductsItem key={product.id} item={product}/>
                );
            })}
        </ListGroup>
    )
} 