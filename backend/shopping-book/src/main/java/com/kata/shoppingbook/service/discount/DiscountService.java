package com.kata.shoppingbook.service.discount;

import com.kata.shoppingbook.model.Book;
import com.kata.shoppingbook.model.Discount;
import com.kata.shoppingbook.model.cart.in.CartItem;
import com.kata.shoppingbook.model.cart.out.ItemWithDiscount;
import com.kata.shoppingbook.service.utils.PriceCalculator;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

@Service
public class DiscountService implements IDiscountService {

    @Override
    public List<ItemWithDiscount> getDiscountForCart(Collection<CartItem> cartItems) {
        List<ItemWithDiscount> itemWithDiscounts = new ArrayList<>();
        List<Book> listOfBooksInItems = extractAllBooksInItems(cartItems);
        do {
            List<Book> groupedBooks = groupedBookAndRemoveTreatedFromOriginalList(listOfBooksInItems);

            Discount discount = DiscountFactory.getDiscount(groupedBooks.size());
            BigDecimal totalPrice = PriceCalculator.getTotalPrice(groupedBooks, discount.getValue());

            itemWithDiscounts.add(
                    new ItemWithDiscount(groupedBooks, discount.getDisplayValue(), totalPrice)
            );
        } while (!CollectionUtils.isEmpty(listOfBooksInItems));
        return itemWithDiscounts;
    }


    private List<Book> groupedBookAndRemoveTreatedFromOriginalList(List<Book> originalListBooks) {
        List<Book> groupedBooks = new ArrayList<>();
        Set<Integer> bookIdSet = new HashSet<>();
        for (int i = 0; i < originalListBooks.size(); i++) {
            if (bookIdSet.add(originalListBooks.get(i).getBookId())) {
                groupedBooks.add(originalListBooks.get(i));
                //remove at this index and make counter to previous value
                originalListBooks.remove(i);
                i--;
            }
        }
        return groupedBooks;
    }

    private List<Book> extractAllBooksInItems(Collection<CartItem> cartItems) {
        List<Book> books = new ArrayList<>();
        for (CartItem item : cartItems) {
            for (int k = 0; k < item.getQuantity(); k++) {
                books.add(new Book(item.getBook()));
            }
        }
        return books;
    }


}
