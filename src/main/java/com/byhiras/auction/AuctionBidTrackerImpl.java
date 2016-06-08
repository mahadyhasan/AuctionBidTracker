package com.byhiras.auction;

import com.byhiras.auction.model.Bid;
import com.byhiras.auction.model.Buyer;
import com.byhiras.auction.model.Item;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Mahady Hasan on 17/04/16.
 */
public class AuctionBidTrackerImpl implements AuctionBidTracker {


    private final Map<Buyer, List<Item>> buyerBidTracker = new ConcurrentHashMap<>();
    private final Map<Item, List<Bid>> itemBidTracker = new ConcurrentHashMap<>();

    /**
     * record a user's bid on an item
     *
     * @param bid
     * @param item
     */
    @Override
    public void recordBid(Bid bid, Item item) {

        Buyer buyer = bid.getBuyer();

        if (!buyerBidTracker.containsKey(buyer)) {
            buyerBidTracker.put(buyer, new ArrayList<>());
        }

        if (!itemBidTracker.containsKey(item)) {
            itemBidTracker.put(item, new ArrayList<>());
        }

        //exceeds starting price
        if (bid.isAcceptableBid(item)) {

            //list of bids on the given item
            List<Bid> listOfBids = itemBidTracker.get(item);

            if (isCurrentBidHighest(bid, listOfBids)) {
                itemBidTracker.get(item).add(bid);
                buyerBidTracker.get(buyer).add(item);
                //updates the buyers that Bid have been placed on this item
                bid.someonePlacedABid(bid, item);

            }

        }


    }

    /**
     * checks current bid is greater than the max bid
     * in the list
     *
     * @param bid        the current bid to be recorded
     * @param listOfBids list of bids on the item
     * @return true if current bid is greater than max bid against
     * the item or no previous bid is records against the item
     * and current bid is first bid in
     */
    private boolean isCurrentBidHighest(Bid bid, List<Bid> listOfBids) {

        if (listOfBids.isEmpty()) return true;

        return bid.getAmount() > listOfBids.stream().
                mapToInt(Bid::getAmount).
                max().
                getAsInt();
    }


    /**
     * Gets all bids for the given item
     *
     * @param item
     * @return
     */
    @Override
    public List<Bid> getAllBids(Item item) {

        return itemBidTracker.get(item);
    }

    /**
     * Get all the items on which a buyer has
     * placed bids on
     *
     * @param buyer the buyer
     * @return
     */
    @Override
    public List<Item> getAllItems(Buyer buyer) {
        return buyerBidTracker.get(buyer);
    }

    /**
     * Get the current winning bid for an item
     *
     * @param item
     * @return
     */
    @Override
    public Bid getCurrentWinningBid(Item item) {

        return itemBidTracker.get(item).stream()
                .max(Comparator.comparing(Bid::getAmount)) //compares Bid amounts
                .get(); //get the stream result
    }
}
