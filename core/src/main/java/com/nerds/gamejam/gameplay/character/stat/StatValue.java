package com.nerds.gamejam.gameplay.character.stat;

public class StatValue {

    private int currentValue;
    private int maxValue;

    public StatValue(int currentValue, int maxValue) {
        this.currentValue = currentValue;
        this.maxValue = maxValue;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void modifyCurrentValue(int toAdd) {
        this.currentValue += toAdd;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void modifyMaxValue(int toAdd) {
        this.maxValue += toAdd;
    }
}
