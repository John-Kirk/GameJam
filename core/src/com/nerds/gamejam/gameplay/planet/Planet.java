package com.nerds.gamejam.gameplay.planet;

public class Planet {

    private final Material baseMaterial;
    private final Material secondaryMaterial;
    private final Landmass landmass;

    public Planet(Material baseMaterial, Material secondaryMaterial, Landmass landmass) {
        this.baseMaterial = baseMaterial;
        this.secondaryMaterial = secondaryMaterial;
        this.landmass = landmass;
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
}
