import React from 'react'
import { Link } from 'react-router-dom'

export default function Header({cartCount}) {
  return (
    <div className='container'>
        <ul className="nav justify-content-end" style={{padding: "20px 0"}}>
            <li className="nav-item">
                <Link className="nav-link active" aria-current="page" to="/books">BOOKS</Link>
            </li>
            <li className="nav-item">
                <Link type="button" className="btn btn-primary position-relative" to="/cart">
                    CART <span className="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">{cartCount}</span>
                </Link>
            </li>
        </ul>
    </div>
    
  )
}