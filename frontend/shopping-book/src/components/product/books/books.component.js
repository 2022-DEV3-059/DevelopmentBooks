
import React, { useEffect, useState } from 'react';
import { getAllBooks } from '../../../services/book.service';
import Book from '../book/book.component';
import './books.component.css';

export default function Books() {

    const [books, setBooks] = useState([]);

    useEffect(() => {
        const data = getAllBooks();
        
        data.then((books) => {
            setBooks(books);
        })

    }, [])


    const bookComponent = books.map(b => {
        return (
            <div key={b.bookId}>
                <Book book={b} />
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