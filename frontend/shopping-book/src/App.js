
import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import Books from './components/product/books/books.component';
import Cart from './components/cart/cart.component';

import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Home from './components/page/home.component';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Home/>} >
          <Route index path='cart' element={<Cart />} />
          <Route path='books' element={<Books/>} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
