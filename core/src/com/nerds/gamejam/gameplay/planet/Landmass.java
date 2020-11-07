package com.nerds.gamejam.gameplay.planet;

public enum Landmass {

    ARCHIPELAGO, LARGE_CONTINENTS, FRACTAL, OVAL, FOUR_CORNERS, PANGAEA, SMALL_CONTINENTS, SPLIT, CRATERS;

    public String getFileName() {
        return "planet/" + toString().toLowerCase() + ".png";
    }
}
