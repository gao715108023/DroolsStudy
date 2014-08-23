package net.drools.bean;

import java.util.List;

/**
 * Created by gaochuanjun on 14-8-23.
 */
public class Customer {

    private String name;

    private int age;

    private List orders;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List getOrders() {
        return orders;
    }

    public void setOrders(List orders) {
        this.orders = orders;
    }
}
