
import React, { useEffect, useState } from 'react';
import { getAllBooks } from '../../../services/book.service';
import { performAddToCart } from '../../../services/cart.service';
import { getSessionToken } from '../../../services/user.service';
import Book from '../book/book.component';
import './books.component.css';

export default function Books({updateCartCountAction}) {

    const [books, setBooks] = useState([]);

    useEffect(() => {
        const data = getAllBooks();

        data.then((books) => {
            setBooks(books);
        })

    }, [])

    const handleAddToCart = (book) => {

        
        console.log("handle add to cart", book);


        const cartIn = {
            sessionToken : getSessionToken(),
            cartItems: [{book : book, quantity: 1}]
        };

        const addToCartResult = performAddToCart(cartIn);

        addToCartResult.then((cartOut) => {
            updateCartCountAction(cartOut?.cartCount);
        })
    }


    const bookComponent = books.map(b => {
        return <Book key={b.bookId} book={b} addToCartAction={handleAddToCart} />
    })


    return (
        <div className='container'>
            <div className='list'>
                {bookComponent}
            </div>
        </div>
    )
}