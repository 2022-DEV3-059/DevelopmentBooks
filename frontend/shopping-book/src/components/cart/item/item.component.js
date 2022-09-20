
import React from 'react'

export default function Item({ book , removeToCartAction}) {
    return (
        <tr>
            <td>
                <img src={book.coverImage} /></td>
            <td>
                <p><strong>{book.title}</strong></p>
                <p>Author : {book.author?.authorName}</p>
                <p>Release year : {book.releaseYear}</p>
            </td>
            <td>
                <p className="text-start text-md-center">
                    <strong>${book.price}</strong>
                </p>
            </td>
            <td>
                <button className='btn btn-danger w-100' onClick={() => removeToCartAction(book)}>Remove</button>
            </td>
        </tr>
    )
}