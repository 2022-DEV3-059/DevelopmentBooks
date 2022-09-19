package com.kata.shoppingbook.dao;

import com.kata.shoppingbook.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthorDAO extends JpaRepository<Author, Integer> {
    Author findByAuthorName(String authorName);
}
