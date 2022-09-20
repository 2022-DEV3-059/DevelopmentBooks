
import React from 'react';
import Book from '../book/book.component';
import './books.component.css';

export default function Books() {

    const books = [
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

    const bookComponent = books.map( b => {
        return (
            <div key={b.bookId}>
                <Book book={b}/>
            </div>
        )
    })


    return (
        <div className='container'>
            <div className='list'>
                {bookComponent}
            </div>
        </div>
    )
}