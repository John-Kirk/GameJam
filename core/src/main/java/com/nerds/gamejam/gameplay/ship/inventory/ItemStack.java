package com.nerds.gamejam.gameplay.ship.inventory;

public class ItemStack {

    private Item item;
    private int amount;

    public ItemStack(Item item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    public Item getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }

    public void modifyAmount(int toAdd) {
        this.amount += amount;
    }
}
