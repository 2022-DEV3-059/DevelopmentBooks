package com.kata.shoppingbook.dao;

import com.kata.shoppingbook.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookDAO extends JpaRepository<Book, Integer> {
}
