import React from 'react'
import Item from './item.component'

export default function ItemsAndDiscount({ itemWithDiscount }) {

    const itemComponents = itemWithDiscount.books.map( b => {
        return (
            <div key={b.bookId}>
                <Item book={b}/>
            </div>
        )
    })

    return (
        <div>
            {itemComponents}
            <p><strong>Discount : {itemWithDiscount.discount}%</strong></p>
            <p className='mb-0'>Price : {itemWithDiscount.totalPrice}$</p>
        </div>
    )
}