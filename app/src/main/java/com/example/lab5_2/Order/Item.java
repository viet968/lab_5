package com.example.lab5_2.Order;

import com.example.lab5_2.Utils.FormatUtils;

import java.io.Serializable;

public class Item implements Serializable {
    private String name;
    private int price, amount;

    public Item(String name, int price, int amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return name+"\t"+FormatUtils.formatNumber(price)+"\t"+amount+" = "+ FormatUtils.formatNumber(amount*price);
    }
}
