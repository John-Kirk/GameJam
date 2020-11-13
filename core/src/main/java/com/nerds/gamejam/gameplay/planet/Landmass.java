package com.nerds.gamejam.gameplay.planet;

public enum Landmass {

    ARCHIPELAGO, LARGE_CONTINENTS, FRACTAL, OVAL, FOUR_CORNERS, PANGAEA, SMALL_CONTINENTS, SPLIT, CRATERS, DIAGONAL, WIGGLES, DEATH_STAR;

    public String getFileName() {
        return "planet/" + toString().toLowerCase() + ".png";
    }

    public String getDisplayName() {
        return toString().toLowerCase().replace("_", " ");
    }
}
