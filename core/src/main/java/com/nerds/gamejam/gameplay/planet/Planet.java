package com.nerds.gamejam.gameplay.planet;

public class Planet {

    private final Material baseMaterial;
    private final Material secondaryMaterial;
    private final Landmass landmass;
    private final float scale;

    public Planet(Material baseMaterial, Material secondaryMaterial, Landmass landmass, float scale) {
        this.baseMaterial = baseMaterial;
        this.secondaryMaterial = secondaryMaterial;
        this.landmass = landmass;
        this.scale = scale;
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

    public float getScale() {
        return scale;
    }
}
