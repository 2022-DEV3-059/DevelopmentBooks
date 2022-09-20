import React, { useEffect, useState } from 'react';
import { getCart, performRemoveToCart } from '../../services/cart.service';
import { getSessionToken } from '../../services/user.service';
import './cart.component.css';
import ItemsAndDiscount from './item/itemanddiscount.component';

export default function Cart({cartCoundUpdater}) {

    const [cart, setCart] = useState({});

    useEffect(() => {
        const cartItemsResult = getCart(getSessionToken());

        cartItemsResult.then((cartOut) => {
            setCart(cartOut);
        })

    }, []);


    const removeToCart = (book) => {
        const cart = {
            sessionToken: getSessionToken(),
            item: {
                book: book,
                quantity: 1
            }
        }
        const removeItemResult = performRemoveToCart(cart);
        removeItemResult.then((cartOut) => {
            setCart(cartOut);
            cartCoundUpdater(cartOut.cartCount);
        })
    }

    const cartItemsComponent = cart.itemsAndDiscount?.map((item, index) => {
        return <ItemsAndDiscount key={index} itemWithDiscount={item} handleRemoveToCart={removeToCart}/>
    });

    return (
        <div className ="container">
            <div className='content'>
                <div style={{width: "75%"}}>
                    <div className='head'>
                        <h5 className="">Cart - {cart.cartCount} items</h5>
                        <p className='totalPrice'>Total Price : {cart.totalPrice}$</p>
                    </div>
                    
                    {cartItemsComponent}
                    <p className='totalPrice itemWithDiscount'>Total Price : {cart.totalPrice}$</p>
                </div>
            </div>
        </div>
    );
}