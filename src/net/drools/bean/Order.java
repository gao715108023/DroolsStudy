package net.drools.bean;

import java.util.List;

/**
 * Created by gaochuanjun on 14-8-23.
 */
public class Order {

    private List items;

    private String name;

    public List getItems() {
        return items;
    }

    public void setItems(List items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
