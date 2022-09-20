package com.kata.shoppingbook.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kata.shoppingbook.model.Author;
import com.kata.shoppingbook.model.Book;
import com.kata.shoppingbook.model.cart.in.CartIn;
import com.kata.shoppingbook.model.cart.in.CartItem;
import com.kata.shoppingbook.model.cart.out.CartOut;
import com.kata.shoppingbook.model.cart.out.ItemWithDiscount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class CartControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Test
    void testAddToCartFor1Call() throws Exception {
        String sessionToken = UUID.randomUUID().toString();

        CartIn cartIn = new CartIn(Collections.singleton(
                new CartItem(
                        new Book(
                                1,
                                "Mock title 1",
                                2022,
                                new Author(1, "author 1"),
                                new BigDecimal(50),
                                null
                        ),
                        1
                )
        ), sessionToken);

        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders.post("/cart")
                                .contentType("application/json")
                                .content(MAPPER.writeValueAsBytes(cartIn)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.itemsAndDiscount").hasJsonPath())
                .andExpect(jsonPath("$.totalPrice").hasJsonPath())
                .andExpect(jsonPath("$.sessionToken").hasJsonPath())
                .andReturn();

        CartOut cartOut = MAPPER.readValue(result.getResponse().getContentAsString(), new TypeReference<CartOut>() {
        });

        assertNotNull(result.getResponse());
        assertEquals(200, result.getResponse().getStatus());
        assertEquals("application/json", result.getResponse().getContentType());

        assertEquals(1, cartOut.getItemsAndDiscount().size());
        assertEquals(sessionToken, cartOut.getSessionToken());
        assertEquals(new BigDecimal(50).setScale(2, RoundingMode.HALF_UP), cartOut.getTotalPrice());
    }

    @Test
    void testAddToCartFor2CallAndSameSessionAndBookWithNoDiscount() throws Exception {
        String sessionToken = UUID.randomUUID().toString();

        CartIn cartIn1 = new CartIn(Collections.singleton(
                new CartItem(
                        new Book(
                                1,
                                "Mock title 1",
                                2022,
                                new Author(1, "author 1"),
                                new BigDecimal(50),
                                null
                        ),
                        1
                )
        ), sessionToken);

        MvcResult result1 = mockMvc.perform(
                        MockMvcRequestBuilders.post("/cart")
                                .contentType("application/json")
                                .content(MAPPER.writeValueAsBytes(cartIn1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.itemsAndDiscount").hasJsonPath())
                .andExpect(jsonPath("$.totalPrice").hasJsonPath())
                .andExpect(jsonPath("$.sessionToken").hasJsonPath())
                .andReturn();

        CartIn cartIn2 = new CartIn(Collections.singleton(
                new CartItem(
                        new Book(
                                1,
                                "Mock title 1",
                                2022,
                                new Author(1, "author 1"),
                                new BigDecimal(50),
                                null
                        ),
                        1
                )
        ), sessionToken);

        MvcResult result2 = mockMvc.perform(
                        MockMvcRequestBuilders.post("/cart")
                                .contentType("application/json")
                                .content(MAPPER.writeValueAsBytes(cartIn2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.itemsAndDiscount").hasJsonPath())
                .andExpect(jsonPath("$.totalPrice").hasJsonPath())
                .andExpect(jsonPath("$.sessionToken").hasJsonPath())
                .andReturn();

        CartOut cartOut = MAPPER.readValue(result2.getResponse().getContentAsString(), new TypeReference<CartOut>() {
        });

        assertNotNull(result2.getResponse());
        assertEquals(200, result2.getResponse().getStatus());
        assertEquals("application/json", result2.getResponse().getContentType());

        assertEquals(2, cartOut.getItemsAndDiscount().size());
        assertEquals(sessionToken, cartOut.getSessionToken());
        assertEquals(new BigDecimal(100).setScale(2, RoundingMode.HALF_UP), cartOut.getTotalPrice());
    }

    @Test
    void testAddToCartFor2CallAndSameSessionAndBookWithDiscount() throws Exception {
        String sessionToken = UUID.randomUUID().toString();

        CartIn cartIn1 = new CartIn(Collections.singleton(
                new CartItem(
                        new Book(
                                1,
                                "Mock title 1",
                                2022,
                                new Author(1, "author 1"),
                                new BigDecimal(50),
                                null
                        ),
                        1
                )
        ), sessionToken);

        MvcResult result1 = mockMvc.perform(
                        MockMvcRequestBuilders.post("/cart")
                                .contentType("application/json")
                                .content(MAPPER.writeValueAsBytes(cartIn1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.itemsAndDiscount").hasJsonPath())
                .andExpect(jsonPath("$.totalPrice").hasJsonPath())
                .andExpect(jsonPath("$.sessionToken").hasJsonPath())
                .andReturn();

        CartIn cartIn2 = new CartIn(Collections.singleton(
                new CartItem(
                        new Book(
                                2,
                                "Mock title 2",
                                2022,
                                new Author(2, "author 2"),
                                new BigDecimal(50),
                                null
                        ),
                        1
                )
        ), sessionToken);

        MvcResult result2 = mockMvc.perform(
                        MockMvcRequestBuilders.post("/cart")
                                .contentType("application/json")
                                .content(MAPPER.writeValueAsBytes(cartIn2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.itemsAndDiscount").hasJsonPath())
                .andExpect(jsonPath("$.totalPrice").hasJsonPath())
                .andExpect(jsonPath("$.sessionToken").hasJsonPath())
                .andReturn();

        CartOut cartOut = MAPPER.readValue(result2.getResponse().getContentAsString(), new TypeReference<CartOut>() {
        });

        assertNotNull(result2.getResponse());
        assertEquals(200, result2.getResponse().getStatus());
        assertEquals("application/json", result2.getResponse().getContentType());

        assertEquals(1, cartOut.getItemsAndDiscount().size());

        List<ItemWithDiscount> itemWithDiscount = new ArrayList<>(cartOut.getItemsAndDiscount());
        assertEquals(2, itemWithDiscount.get(0).getBooks().size());
        assertEquals(5d, itemWithDiscount.get(0).getDiscount());
        assertEquals(new BigDecimal(95).setScale(2, RoundingMode.HALF_UP), itemWithDiscount.get(0).getTotalPrice());

        assertEquals(sessionToken, cartOut.getSessionToken());
        assertEquals(new BigDecimal(95).setScale(2, RoundingMode.HALF_UP), cartOut.getTotalPrice());
    }

    @Test
    void testGetCartForASession() throws Exception {
        // add to cart
        String sessionToken = UUID.randomUUID().toString();

        CartIn cartIn = new CartIn(Collections.singleton(
                new CartItem(
                        new Book(
                                1,
                                "Mock title 1",
                                2022,
                                new Author(1, "author 1"),
                                new BigDecimal(50),
                                null
                        ),
                        1
                )
        ), sessionToken);

        mockMvc.perform(
        MockMvcRequestBuilders.post("/cart")
                .contentType("application/json")
                .content(MAPPER.writeValueAsBytes(cartIn)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.itemsAndDiscount").hasJsonPath())
        .andExpect(jsonPath("$.totalPrice").hasJsonPath())
        .andExpect(jsonPath("$.sessionToken").hasJsonPath())
        .andReturn();

        //get cart
        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders.get("/cart/" + sessionToken)
                                .contentType("application/json")
                                .content(MAPPER.writeValueAsBytes(cartIn)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.itemsAndDiscount").hasJsonPath())
                .andExpect(jsonPath("$.totalPrice").hasJsonPath())
                .andExpect(jsonPath("$.sessionToken").hasJsonPath())
                .andReturn();

        assertEquals(200, result.getResponse().getStatus());
        assertEquals("application/json", result.getResponse().getContentType());

        CartOut cartOut = MAPPER.readValue(result.getResponse().getContentAsString(), new TypeReference<CartOut>() {
        });

        assertEquals(1, cartOut.getItemsAndDiscount().size());
    }
}
