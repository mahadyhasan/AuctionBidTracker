package com.byhiras.auction.exception;

/**
 * Created by Mahady Hasan on 17/04/16.
 */
public class InvalidBidException extends RuntimeException {

    public InvalidBidException(String reason) {
        super(reason);
    }
}
