package com.nerds.gamejam.gameplay.planet;

import java.util.Random;

public class PlanetFactory {

    private Random random;

    public PlanetFactory() {
        this.random = new Random();
    }

    public Planet createPlanet(int x, int y) {
        Material baseMaterial = randomEnum(Material.class);
        Material secondaryMaterial = null;
        while (secondaryMaterial == null || secondaryMaterial == baseMaterial) {
            secondaryMaterial = randomEnum(Material.class);
        }
        Landmass landmass = randomEnum(Landmass.class);

        float planetScale = random.nextFloat()+0.5f;
        float width = planetScale * 128;
        float height = planetScale * 128;

        return new Planet(baseMaterial, secondaryMaterial, landmass, x, y, width, height);
    }

    public <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

}
