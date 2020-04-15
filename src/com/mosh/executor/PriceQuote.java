package com.mosh.executor;

public class PriceQuote {
    public int getPrice() {
        return price;
    }

    private final int price;
    private final String site;

    @Override
    public String toString() {
        return "{" +
                "price=" + price +
                ", site='" + site + '\'' +
                '}';
    }

    public PriceQuote(int price, String site) {
        this.price = price;
        this.site = site;
    }
}
