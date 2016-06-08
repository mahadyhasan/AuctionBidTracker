package com.byhiras.auction;

import com.byhiras.auction.model.Item;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created by Mahady Hasan on 16/04/16.
 */
public class TestAuctionSetup {


    private final Item item = new Item("Iphone6s", "Apple latest smartphone", 100, 350);


    @Test
    public void testItemNameMatches() {
        String name = "Iphone6s";
        assertEquals(name, item.getName());
    }

}
