package com.byhiras.auction;

import com.byhiras.auction.model.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Mahady Hasan on 16/04/16.
 */
public class AuctionBiddingActivity {

    private Buyer buyer;
    private Auction auction = new Auction();
    private AuctionBidTracker bidTracker = new AuctionBidTrackerImpl();

    AuctionOrchestrator orchestrator = new AuctionOrchestrator(auction, bidTracker);

    Item item1 = new Item("Item1", "Almost like new", 10, 100);
    Item item2 = new Item("Item2", "Very good", 30, 200);
    Item item3 = new Item("Item3", "A used item", 50, 700);
    Item item4 = new Item("Item4", "Apple Smart phone", 10, 1000);


    @Before
    public void setup() {

        buyer = new AuctionBuyer(1, "Mahady");

        auction.addItem(item1);
        auction.addItem(item2);
        auction.addItem(item3);
        auction.addItem(item4);
    }


    @Test
    public void testBidWithAmountGreaterThanStartPrice() throws Exception {
        Item item = orchestrator.getAuctionItem("Item1");
        Bid bid = new AuctionBid(buyer, 150);
        assertEquals(bid.isAcceptableBid(item), true);
    }

    @Test
    public void testBidWithAmountLessThanStartPrice() throws Exception {
        Item item = orchestrator.getAuctionItem("Item4");
        Bid bid = new AuctionBid(buyer, 5);
        assertEquals(bid.isAcceptableBid(item), false);
    }

    @Test
    public void testBidWithAmountEquallingStartPrice() throws Exception {
        Item item = orchestrator.getAuctionItem("Item4");
        Bid bid = new AuctionBid(buyer, 10);
        assertEquals(bid.isAcceptableBid(item), false);
    }

    @Test
    public void testBidRecordWithLowerAmount() throws Exception {
        Item item = orchestrator.getAuctionItem("Item2");
        Bid bid = new AuctionBid(buyer, 20);
        orchestrator.placeBid(bid, item);

        //expect bid to not record
        AuctionBidTracker bt = orchestrator.getBidTracker();
        assertTrue(bt.getAllItems(buyer).isEmpty());
    }

    @Test
    public void testBidRecordWithEqualAmount() throws Exception {
        Item item = orchestrator.getAuctionItem("Item2");
        Bid bid = new AuctionBid(buyer, 30);
        orchestrator.placeBid(bid, item);

        //expect bid to not record
        AuctionBidTracker bt = orchestrator.getBidTracker();
        assertTrue(bt.getAllItems(buyer).isEmpty());
    }

    @Test
    public void testBidRecordWithHigherAmount() throws Exception {
        Item item = orchestrator.getAuctionItem("Item2");
        Bid bid = new AuctionBid(buyer, 50);
        orchestrator.placeBid(bid, item);

        //expect bid to record
        AuctionBidTracker bt = orchestrator.getBidTracker();
        assertFalse(bt.getAllItems(buyer).isEmpty());
        assertTrue(bt.getCurrentWinningBid(item).equals(bid));
    }

    @Test
    public void testCoupleBidsWithOneHigher() throws Exception {
        Bid bid1 = new AuctionBid(buyer, 200);
        Bid bid2 = new AuctionBid(buyer, 500);
        orchestrator.placeBid(bid1, item4);
        orchestrator.placeBid(bid2, item4);

        assertTrue(orchestrator.getBidTracker().getCurrentWinningBid(item4).equals(bid2));
    }

    @Test
    public void testGetAllBidsForOneItem() throws Exception {
        Buyer buyer2 = new AuctionBuyer(2, "John Smith");
        Bid bid1 = new AuctionBid(buyer, 60);
        Bid bid2 = new AuctionBid(buyer, 70);
        Bid bid3 = new AuctionBid(buyer2, 80);

        orchestrator.placeBid(bid1, item3);
        orchestrator.placeBid(bid2, item3);
        orchestrator.placeBid(bid3, item3);

        assertTrue("Total number of bids in list is 3", orchestrator.getBidTracker().getAllBids(item3).size() == 3);
    }

    @Test
    public void testGetAllItemOnWhichUserHasBidOn() throws Exception {
        Bid bid1 = new AuctionBid(buyer, 70);
        Bid bid2 = new AuctionBid(buyer, 80);
        Bid bid3 = new AuctionBid(buyer, 90);
        Bid bid4 = new AuctionBid(buyer, 150);

        orchestrator.placeBid(bid1, item1);
        orchestrator.placeBid(bid2, item3);
        orchestrator.placeBid(bid3, item3);
        orchestrator.placeBid(bid4, item4);

        assertTrue(orchestrator.getBidTracker().getAllBids(item3).size() == 2);

    }

    @Test
    public void testSameValueBidOnAItem() throws Exception {

        Bid bid1 = new AuctionBid(buyer, 50);
        Bid bid2 = new AuctionBid(buyer, 50);

        orchestrator.placeBid(bid1, item1);
        orchestrator.placeBid(bid2, item1);

        assertTrue(orchestrator.getBidTracker().getAllItems(buyer).size() == 1);
        assertTrue(orchestrator.getBidTracker().getCurrentWinningBid(item1).equals(bid1));

    }

    @Test
    public void givenMultipleBidsTestWinningBid() {
        Bid bid1 = new AuctionBid(buyer, 25);
        Bid bid2 = new AuctionBid(buyer, 35);
        Bid bid3 = new AuctionBid(buyer, 45);
        Bid bid4 = new AuctionBid(buyer, 1000);
        Bid bid5 = new AuctionBid(buyer, 5);
        Bid bid6 = new AuctionBid(buyer, 2);
        Bid bid7 = new AuctionBid(buyer, 50);

        orchestrator.placeBid(bid1, item1);
        orchestrator.placeBid(bid2, item1);
        orchestrator.placeBid(bid3, item1);
        orchestrator.placeBid(bid4, item1);
        orchestrator.placeBid(bid5, item1);
        orchestrator.placeBid(bid6, item1);
        orchestrator.placeBid(bid7, item1);

        assertEquals(4, orchestrator.getBidTracker().getAllBids(item1).size());
        assertTrue(orchestrator.getBidTracker().getCurrentWinningBid(item1).equals(bid4));


    }


}
