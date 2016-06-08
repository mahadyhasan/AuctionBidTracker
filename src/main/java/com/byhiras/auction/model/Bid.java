package com.byhiras.auction.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mahady Hasan on 16/04/16.
 */

/**
 * Abstract Subject Class
 */
public abstract class Bid {

    //Observer
    private List<Buyer> buyerObservers = new ArrayList<>();

    public void attach(Buyer buyer) {
        buyerObservers.add(buyer);
    }

    public abstract Buyer getBuyer();

    public abstract boolean isAcceptableBid(Item item);

    public abstract int getAmount();

    public abstract void someonePlacedABid(Bid bid, Item item);

    public void notifyObservers(Bid bid, Item item) {
        for (Buyer observer : buyerObservers) {
            observer.update(bid, item);
        }
    }


}
