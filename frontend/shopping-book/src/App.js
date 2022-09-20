
import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import Books from './components/product/books/books.component';
import Cart from './components/cart/cart.component';

import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Home from './components/page/home.component';
import { useEffect, useState } from 'react';
import { getSessionToken } from './services/user.service';
import { getCart } from './services/cart.service';

function App() {

  const [cartCount, setCartCount] = useState(0);

  useEffect(()=>{
    const cartItemsResult = getCart(getSessionToken());

    cartItemsResult.then((cartOut) => {
      setCartCount(cartOut.cartCount);
    })
    
  },[]);

  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Home cartCount={cartCount}/>} >
          <Route index path='cart' element={<Cart />} />
          <Route path='books' element={<Books updateCartCountAction={setCartCount}/>} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
