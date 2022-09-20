import React from 'react'
import Item from './item.component'
import './itemanddiscount.component.css'

export default function ItemsAndDiscount({ itemWithDiscount, handleRemoveToCart }) {

    const itemComponents = itemWithDiscount.books.map( b => {
        return <Item key={b.bookId} book={b} removeToCartAction={handleRemoveToCart}/>
    })

    const displayDiscount = itemWithDiscount.discount != 0 ? (
                <div className='table price-discount'>
                    <p><strong>Discount : {itemWithDiscount.discount}%</strong></p>
                    <p className='mb-0'>Price : {itemWithDiscount.totalPrice}$</p>
                </div>
            ) : null

    return (
        <div className='itemWithDiscount'>
           <table className='table'>
                <tbody>
                    {itemComponents}
                </tbody>
            </table>
            {displayDiscount}
        </div>
        
    )
}