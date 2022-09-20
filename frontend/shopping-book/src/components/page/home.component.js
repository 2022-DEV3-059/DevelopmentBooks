import React, { useEffect } from 'react'
import { Outlet, useNavigate } from 'react-router-dom'
import Header from './header.component'

export default function Home({ cartCount }) {
    const navigate = useNavigate();

    useEffect(() => {
        return navigate("/books")
    }, []);
    return (
        <div>
            <Header cartCount={cartCount} />
            <Outlet />
        </div>
    )
}