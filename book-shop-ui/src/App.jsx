// src/App.js
import React, { useState } from "react";
import BookList from "./Components/BookList";
import Cart from "./Components/Cart";
import axios from "axios";

const App = () => {
  const [cart, setCart] = useState([]);

  const addToCart = (bookId) => {
    // Make a request to your backend to add the book to the cart
    axios
      .post("http://localhost:8088/BOOKSHOPs/addcart", { bookId })
      .then((response) => {
        // Assuming your backend returns the updated cart
        setCart(response.data.cart);
      })
      .catch((error) => {
        console.error("Error adding to cart:", error);
      });
  };

  return (
    <div>
      <h1>Bookshop</h1>
      <BookList addToCart={addToCart} />
      <Cart cartItems={cart} />
    </div>
  );
};

export default App;
