package com.nerds.gamejam.gameplay.character;

import com.badlogic.gdx.utils.I18NBundle;
import com.nerds.gamejam.translation.GameStrings;

public class Character {

    private float totalHp;
    private float currentHp;
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;
    private I18NBundle strings;

    public Character() {
        strings = GameStrings.getInstance();
        totalHp = 20;
        currentHp = 20;
    }

    public String getCurrentHpDescription() {
        int hpPercentage = (int) ((currentHp/totalHp) * 100);
        if (hpPercentage > 89) {
            return strings.get("healthyStatus");
        } else if (hpPercentage > 65) {
            return strings.get("lightlyWoundedStatus");
        } else if (hpPercentage > 45) {
            return strings.get("woundedStatus");
        } else if (hpPercentage > 15) {
            return strings.get("badlyWoundedStatus");
        } else if (hpPercentage > 0) {
            return strings.get("nearDeathStatus");
        }
        return strings.get("deadStatus");
    }

    public float getTotalHp() {
        return this.totalHp;
    }

    public void setTotalHp(int totalHp) {
        this.totalHp = totalHp;
    }

    public float getCurrentHp() {
        return this.currentHp;
    }

    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
    }
}
