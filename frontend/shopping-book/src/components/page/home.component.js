import React from 'react'
import { Outlet } from 'react-router-dom'
import Header from './header.component'

export default function Home() {
  return (
    <div>
        <Header/>
        <Outlet/>
    </div>
  )
}