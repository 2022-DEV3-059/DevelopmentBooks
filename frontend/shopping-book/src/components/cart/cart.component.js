import React, { useEffect, useState } from 'react';
import './cart.component.css';
import ItemsAndDiscount from './itemanddiscount.component';

export default function Cart() {

    const cartItems= {
        itemsAndDiscount : [
            {
                discount : 5,
                totalPrice : 95,
                books : [
                    {
                        bookId: 1,
                        title: "Book title 1",
                        releaseYear: 2020,
                        author: {authorId : 1 , authorName : "author name 1"},
                        coverImage: "https://raw.githubusercontent.com/stephane-genicot/katas/master/images/Kata_DevelopmentBooks_CleanCode.png",
                        price: 50
                    },
                    {
                        bookId: 2,
                        title: "Book title 2",
                        releaseYear: 2022,
                        author: {authorId : 2 , authorName : "author name 2"},
                        coverImage: "https://raw.githubusercontent.com/stephane-genicot/katas/master/images/Kata_DevelopmentBooks_CleanCoder.png",
                        price: 70
                    },
                ]
            },
            {
                discount : 5,
                totalPrice : 95,
                books : [
                    {
                        bookId: 1,
                        title: "Book title 1",
                        releaseYear: 2020,
                        author: {authorId : 1 , authorName : "author name 1"},
                        coverImage: "https://raw.githubusercontent.com/stephane-genicot/katas/master/images/Kata_DevelopmentBooks_CleanCode.png",
                        price: 50
                    },
                    {
                        bookId: 2,
                        title: "Book title 2",
                        releaseYear: 2022,
                        author: {authorId : 2 , authorName : "author name 2"},
                        coverImage: "https://raw.githubusercontent.com/stephane-genicot/katas/master/images/Kata_DevelopmentBooks_CleanCoder.png",
                        price: 70
                    },
                ]
            }
        ],
        totalPrice : 95
    }

    const cart = cartItems;


    const cartItemsComponent = cart.itemsAndDiscount.map( i => {
        return (
            <div key={i}>
                <ItemsAndDiscount itemWithDiscount={i}/>
            </div>
        )
    })


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
    )
}