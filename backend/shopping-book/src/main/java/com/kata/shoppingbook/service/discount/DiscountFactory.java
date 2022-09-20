package com.kata.shoppingbook.service.discount;

import com.kata.shoppingbook.model.Discount;


public class DiscountFactory {

    /**
     * Provide the discount for items
     *
     * @param itemCount the item's count
     * @return discount
     */
    public static Discount getDiscount(int itemCount) {
        // TODO : Make these rules to database
        switch (itemCount) {
            case 2:
                //5%
                return new Discount(5d, 0.95);
            case 3:
                //10%
                return new Discount(10d, 0.90);
            case 4:
                //20%
                return new Discount(20d, 0.80);
            case 5:
                //25%
                return new Discount(25d, 0.75);

            default:
                return new Discount(0d, 0d);
        }
    }
}
