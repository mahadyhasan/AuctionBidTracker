package com.byhiras.auction.model;

/**
 * Created by Mahady on 16/04/16.
 */

/**
 * The AuctionBid class is an implementation
 * of Bid class and represents a buyer's bid
 * <p>
 * This is a concrete Subject class
 * in the Observable pattern
 */
public class AuctionBid extends Bid {

    private final Buyer buyer;
    private final int amount;

    public AuctionBid(Buyer buyer, int amount) {

        if (amount < 0) throw new RuntimeException("Negative bid value is not allowed: " + amount);

        this.buyer = buyer;
        this.amount = amount;

        //attach a buyer to subject so buyer client can be notified
        attach(buyer);
    }


    @Override
    public Buyer getBuyer() {
        return buyer;
    }

    /**
     * An acceptable is bid is one that exceeds
     * the starting price of an item
     *
     * @param item the item in the auction to lookup
     * @return true if the amount exceeds initial start price
     */
    @Override
    public boolean isAcceptableBid(Item item) {
        return amount > item.getStartPrice();
    }

    @Override
    public int getAmount() {
        return amount;
    }

    /**
     * Inform our buyer observers when a bid
     * has been places on an item
     *
     * @param bid
     * @param item
     */
    @Override
    public void someonePlacedABid(Bid bid, Item item) {
        notifyObservers(bid, item);
    }

    @Override
    public String toString() {
        return "" + amount;
    }
}
