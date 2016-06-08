package com.byhiras.auction.model;

/**
 * Created by Mahady Hasan on 16/04/16.
 */
public class Item {

    private final String name;
    private final String description;
    private final int startPrice;
    private final int reservePrice;

    public Item(String name, String description, int startPrice, int reservePrice) {
        this.name = name;
        this.description = description;
        this.startPrice = startPrice;
        this.reservePrice = reservePrice;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getStartPrice() {
        return startPrice;
    }

    public int getReservePrice() {
        return reservePrice;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (!name.equals(item.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                        ", description='" + description + '\'' +
                        ", startPrice=" + startPrice +
                        ", reservePrice=" + reservePrice +
                        '}';
    }
}
