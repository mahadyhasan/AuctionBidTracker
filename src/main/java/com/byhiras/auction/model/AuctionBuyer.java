package com.byhiras.auction.model;

/**
 * Created by Mahady Hasan on 16/04/16.
 */

/**
 * AuctionBuyer is treated as the concrete Observer
 */
public class AuctionBuyer extends Buyer {

    private final int id;
    private final String name;

    public AuctionBuyer(int id, String name) {
        this.id = id;
        this.name = name;
    }


    /**
     * This method is called whenever the observed object is changed (Bid). An application calls an
     * Observable object's notifyObservers method to have all the object's observers (Buyers)
     * notified of the change.
     */
    @Override
    void update(Bid bid, Item item) {
        System.out.println(name + " placed a bid of " + bid
                + " on " + item + "] ");
    }
}

