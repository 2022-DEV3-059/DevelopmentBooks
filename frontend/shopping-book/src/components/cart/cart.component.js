import React, { useEffect, useState } from 'react';
import { getCart } from '../../services/cart.service';
import './cart.component.css';
import ItemsAndDiscount from './item/itemanddiscount.component';

export default function Cart() {

    const [cart, setCart] = useState({});

    useEffect(() => {
        const cartItemsResult = getCart("sessionToken");

        cartItemsResult.then((cartOut) => {
            setCart(cartOut);
        })

    }, []);

    const cartItemsComponent = cart.itemsAndDiscount?.map((item, index) => {
        return (
            <div key={index}>
                <ItemsAndDiscount itemWithDiscount={item} />
            </div>
        )
    });

    return (
        <div>
            <section className="h-100 gradient-custom">
                <div className="container py-5">
                    <div className="row d-flex justify-content-center my-4">
                        <div className="col-md-12">
                            <div className="card mb-12">
                                <div className="card-header py-3">
                                    <h5 className="mb-0">Cart - 2 items</h5>
                                </div>
                                <div className="card-body">
                                    {cartItemsComponent}
                                    <p className='mb-0 right'>Total Price : {cart.totalPrice}$</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    );
}