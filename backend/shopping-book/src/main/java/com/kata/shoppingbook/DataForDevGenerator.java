package com.kata.shoppingbook;

import com.kata.shoppingbook.dao.IAuthorDAO;
import com.kata.shoppingbook.dao.IBookDAO;
import com.kata.shoppingbook.model.Author;
import com.kata.shoppingbook.model.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.util.Arrays;

@Component
@Profile({"dev","test"})
public class DataForDevGenerator implements CommandLineRunner {

    @Value("${server.port}")
    private String port;

    private final IBookDAO bookDAO;

    private final IAuthorDAO authorDAO;

    public DataForDevGenerator(IBookDAO bookDAO, IAuthorDAO authorDAO) {
        this.bookDAO = bookDAO;
        this.authorDAO = authorDAO;
    }

    @Override
    public void run(String... args) throws Exception {
        populateAuthor();
        populateBook();
    }

    private void populateAuthor() {
        Author author1 = new Author(1, "Robert Martin");
        Author author2 = new Author(2, "Kent Beck");
        Author author3 = new Author(3, "Michael C. Feathers");

        authorDAO.saveAll(Arrays.asList(author1, author2, author3));
    }

    private void populateBook() {
        String img1 = "http://" + InetAddress.getLoopbackAddress().getHostAddress() + ":" + port + "/images/clean-code.png";
        Book book1 = new Book(
                1,
                "Clean Code",
                2008,
                authorDAO.findByAuthorName("Robert Martin"),
                new BigDecimal(50),
                img1
        );

        String img2 = "http://" + InetAddress.getLoopbackAddress().getHostAddress() + ":" + port + "/images/clean-coder.png";
        Book book2 = new Book(
                2,
                "The Clean Coder",
                2011,
                authorDAO.findByAuthorName("Robert Martin"),
                new BigDecimal(50),
                img2
        );

        String img3 = "http://" + InetAddress.getLoopbackAddress().getHostAddress() + ":" + port + "/images/clean-coder.png";
        Book book3 = new Book(
                3,
                "Clean Architecture",
                2017,
                authorDAO.findByAuthorName("Robert Martin"),
                new BigDecimal(50),
                img3
        );

        String img4 = "http://" + InetAddress.getLoopbackAddress().getHostAddress() + ":" + port + "/images/test-driven-development.jpeg";
        Book book4 = new Book(
                4,
                "Test Driven Development by Example",
                2003,
                authorDAO.findByAuthorName("Kent Beck"),
                new BigDecimal(50),
                img4
        );

        String img5 = "http://" + InetAddress.getLoopbackAddress().getHostAddress() + ":" + port + "/images/clean-architecture.jpeg";
        Book book5 = new Book(
                5,
                "Working Effectively With Legacy Code",
                2004,
                authorDAO.findByAuthorName("Michael C. Feathers"),
                new BigDecimal(50),
                img5
        );

        bookDAO.saveAll(Arrays.asList(book1, book2, book3, book4, book5));


    }
}
