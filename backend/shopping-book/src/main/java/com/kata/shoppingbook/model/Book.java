package com.kata.shoppingbook.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Book {
    private Integer bookId;
    private String title;
    private Integer releaseYear;
    private Author author;
    private BigDecimal price;
    private String coverImage;

    public Book() {
    }

    public Book(Integer bookId, String title, Integer releaseYear, Author author, BigDecimal price, String coverImage) {
        this.bookId = bookId;
        this.title = title;
        this.releaseYear = releaseYear;
        this.author = author;
        this.price = price;
        this.coverImage = coverImage;
    }

    public Book(Book anotherBook) {
        this.bookId = anotherBook.bookId;
        this.title = anotherBook.title;
        this.releaseYear = anotherBook.releaseYear;
        this.author = anotherBook.author;
        this.price = anotherBook.price;
        this.coverImage = anotherBook.coverImage;
    }


    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return Objects.equals(bookId, book.bookId);
    }

    @Override
    public int hashCode() {
        return bookId != null ? bookId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", releaseYear=" + releaseYear +
                ", author=" + author +
                ", price=" + price +
                ", coverImage='" + coverImage + '\'' +
                '}';
    }
}
