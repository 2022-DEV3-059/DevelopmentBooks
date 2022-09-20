import React from 'react'
import { Outlet } from 'react-router-dom'
import Header from './header.component'

export default function Home({cartCount}) {
  return (
    <div>
        <Header cartCount={cartCount}/>
        <Outlet/>
    </div>
  )
}