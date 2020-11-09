package com.nerds.gamejam.gameplay.planet;

import com.nerds.gamejam.util.RandomSeed;

public class PlanetFactory {

    private final RandomSeed randomSeed;

    public PlanetFactory(RandomSeed randomSeed) {
        this.randomSeed = randomSeed;
    }

    public Planet createPlanet(int x, int y) {
        Material baseMaterial = randomEnum(Material.class);
        Material secondaryMaterial = null;
        while (secondaryMaterial == null || secondaryMaterial == baseMaterial) {
            secondaryMaterial = randomEnum(Material.class);
        }
        Landmass landmass = null;
        while (landmass == null || landmass == Landmass.DEATH_STAR) {
            landmass = randomEnum(Landmass.class);
        }

        if (shouldBeDeathStar()) {
            baseMaterial = Material.METAL;
            secondaryMaterial = Material.METAL_2;
            landmass = Landmass.DEATH_STAR;
        }

        float planetScale = randomSeed.getRandomGenerator().nextFloat()+0.5f;
        float width = planetScale * 128;
        float height = planetScale * 128;

        return new Planet(baseMaterial, secondaryMaterial, landmass, x, y, width, height);
    }

    private boolean shouldBeDeathStar() {
        return randomSeed.getRandomGenerator().nextInt(101) == 100;
    }

    public <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int x = randomSeed.getRandomGenerator().nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

}
