package com.nerds.gamejam.gameplay.ship.inventory;

import java.util.LinkedList;
import java.util.List;

public class Inventory {

    private List<ItemStack> items;

    public Inventory() {
        this.items = new LinkedList<>();
    }

    public List<ItemStack> getItems() {
        return items;
    }

    public void addItem(ItemStack itemStack, int index) {
        items.add(index, itemStack);
    }

    public ItemStack removeItem(int index) {
        return items.remove(index);
    }
}
