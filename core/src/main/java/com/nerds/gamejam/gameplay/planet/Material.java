package com.nerds.gamejam.gameplay.planet;

import com.badlogic.gdx.graphics.Color;

public enum Material {

    WATER(3,169,244, 1),
    LAVA(255,87,34, 1),
    SAND(255,235,59, 1),
    GRAVEL(121,85,72, 1),
    RED_SAND(244,67,54, 1),
    GRASS(118,255,3, 1),
    ROCK(96,125,139, 1),
    BASALT(33,33,33, 1),
    METAL(75, 75, 75, 1),
    METAL_2(66, 66, 66, 1);

    private Color color;

    Material(int r, int g, int b, float a) {
        this.color = new Color(r / 255f, g / 255f, b / 255f, a);
    }

    public Color getColor() {
        return color;
    }

    public String getDisplayName() {
        return toString().toLowerCase().replace("_", " ");
    }
}
