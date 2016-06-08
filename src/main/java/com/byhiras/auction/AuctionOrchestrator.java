package com.byhiras.auction;

import com.byhiras.auction.exception.InvalidBidException;
import com.byhiras.auction.model.Auction;
import com.byhiras.auction.model.Bid;
import com.byhiras.auction.model.Item;

/**
 * Created by Mahady Hasan on 16/04/16.
 */
public class AuctionOrchestrator {

    private final Auction auction;
    private final AuctionBidTracker bidTracker;

    public AuctionOrchestrator(Auction auction, AuctionBidTracker bidTracker) {
        this.auction = auction;
        this.bidTracker = bidTracker;
    }

    public Item getAuctionItem(String itemName) {
        return auction.getItemByName(itemName);
    }

    public void placeBid(Bid bid, Item item) {

        if (bid.getBuyer() == null) {
            throw new InvalidBidException("Bid Buyer cannot be null");
        }

        if (bid == null) {
            throw new InvalidBidException("Bid cannot be null");
        }

        if (item == null) {
            throw new InvalidBidException("Item cannot be null");
        }

        //register the bid in the book
        bidTracker.recordBid(bid, item);


    }


    public AuctionBidTracker getBidTracker() {
        return bidTracker;
    }

}
