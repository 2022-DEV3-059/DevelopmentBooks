
import React from 'react'

export default function Item({ book }) {
    return (
        <div>
            <div className="row">
                <div className="col-lg-3 col-md-12 mb-4 mb-lg-0">
                    <div className="bg-image hover-overlay hover-zoom ripple rounded" data-mdb-ripple-color="light">
                        <img src={book.coverImage}
                            className="w-100" />
                    </div>
                </div>

                <div className="col-lg-5 col-md-6 mb-4 mb-lg-0">
                    <p><strong>{book.title}</strong></p>
                    <p>Author : {book.author?.authorName}</p>
                    <p>Release year : {book.releaseYear}</p>
                </div>

                <div className="col-lg-4 col-md-6 mb-4 mb-lg-0">
                    <p className="text-start text-md-center">
                        <strong>${book.price}</strong>
                    </p>
                </div>
            </div>
            <hr className="my-4" />
        </div>
    )
}