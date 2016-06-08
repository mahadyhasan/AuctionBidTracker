package com.byhiras.auction;

import com.byhiras.auction.model.Bid;
import com.byhiras.auction.model.Buyer;
import com.byhiras.auction.model.Item;

import java.util.List;

/**
 * Created by Mahady Hasan on 17/04/16.
 */


public interface AuctionBidTracker {

    void recordBid(Bid bid, Item item);

    List<Bid> getAllBids(Item item);

    List<Item> getAllItems(Buyer buyer);

    Bid getCurrentWinningBid(Item item);


}
