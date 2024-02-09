import React, { useState, useEffect } from "react";
import axios from "axios";

const BookList = ({ addToCart }) => {
  const [books, setBooks] = useState([]);

  useEffect(() => {
    // Fetch books from the backend
    axios.get("http://localhost:8088/BOOKSHOPs/books").then((response) => {
      setBooks(response.data);
    });
  }, []);

  return (
    <div className="book-list">
      <h2>Book List</h2>
      <ul>
        {books.map((book) => (
          <li key={book.id}>
            <img src={`images/book${book.id}.jpg`} alt={book.name} />
            <div className="book-details">
              <h3>{book.name}</h3>
              <p>Author: {book.author}</p>
              <p>Price: ${book.price}</p>
              <button onClick={() => addToCart(book.id)}>Add to Cart</button>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default BookList;
