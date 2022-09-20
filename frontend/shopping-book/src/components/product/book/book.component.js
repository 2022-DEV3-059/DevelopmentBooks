import React from 'react'

export default function Book({book}) {
  return (
    <div>
        <div className="card" style={{width: "18rem"}}>
            <img src={book.coverImage} className="card-img-top" alt="alt img" />
            <div className="card-body">
                <h5 className="card-title">{book.title}</h5>
                <h6 className="card-subtitle mb-2 text-muted">{book.author?.authorName}</h6>
                <p className="card-text">{book.releaseYear}</p>
                <p>{book.price} $</p>
                <button  className="btn btn-primary">ADD TO CART</button>
            </div>
        </div>
    </div>
  )
}