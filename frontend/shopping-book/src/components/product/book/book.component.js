import React from 'react'
import './book.component.css'

export default function Book({book, addToCartAction}) {
  return (
    <div className="card" >
        <img src={book.coverImage} className="card-img-top book-cover" alt="alt img" />
        <div className="card-body">
            <h5 className="card-title">{book.title}</h5>
            <h6 className="card-subtitle mb-2 text-muted">{book.author?.authorName}</h6>
            <p className="card-text">{book.releaseYear}</p>
            <p>{book.price} $</p>
            <button  className="btn btn-primary" onClick={() => addToCartAction(book)}>ADD TO CART</button>
        </div>
    </div>
  )
}