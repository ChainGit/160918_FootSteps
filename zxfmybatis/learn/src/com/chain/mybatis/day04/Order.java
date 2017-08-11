package com.chain.mybatis.day04;

public class Order {

    private int id;
    private String name;
    private int amount;

    public Order(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public Order() {

    }

    public Order(int id, String name, int amount) {

        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    public Order(int id) {

        this.id = id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
