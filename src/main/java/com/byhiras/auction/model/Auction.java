package com.byhiras.auction.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mahady Hasan on 16/04/16.
 */
public class Auction {

    private List<Item> items = new ArrayList<Item>();

    public void addItem(Item item) {
        items.add(item);
    }


    /**
     * @param itemName the name of the item to look for
     * @return the item that is found
     */
    public Item getItemByName(final String itemName) {

        Item item = items.stream().filter(
                (i) -> i.getName().equals(itemName))
                .findFirst().get();

        return item;
    }
}
