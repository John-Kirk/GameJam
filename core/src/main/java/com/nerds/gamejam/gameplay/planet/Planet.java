package com.nerds.gamejam.gameplay.planet;

public class Planet {

    private final Material baseMaterial;
    private final Material secondaryMaterial;
    private final Landmass landmass;
    private final int x;
    private final int y;
    private final float width;
    private final float height;

    public Planet(Material baseMaterial, Material secondaryMaterial, Landmass landmass, int x, int y, float width, float height) {
        this.baseMaterial = baseMaterial;
        this.secondaryMaterial = secondaryMaterial;
        this.landmass = landmass;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Material getBaseMaterial() {
        return baseMaterial;
    }

    public Material getSecondaryMaterial() {
        return secondaryMaterial;
    }

    public Landmass getLandmass() {
        return landmass;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
