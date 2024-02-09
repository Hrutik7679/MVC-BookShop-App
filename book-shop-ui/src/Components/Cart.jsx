import React from "react";

const Cart = ({ cartItems }) => {
  return (
    <div className="shopping-cart">
      <h2>Shopping Cart</h2>
      <ul>
        {cartItems.map((item) => (
          <li key={item.id}>
            <img src={`images/book${item.id}.jpg`} alt={item.name} />
            <div className="cart-details">
              <h3>{item.name}</h3>
              <p>Author: {item.author}</p>
              <p>Price: ${item.price}</p>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Cart;
