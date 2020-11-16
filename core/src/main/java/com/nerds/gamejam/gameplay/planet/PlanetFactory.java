package com.nerds.gamejam.gameplay.planet;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.graphics.Color;
import com.nerds.gamejam.GameJam;
import com.nerds.gamejam.ecs.component.CircleComponent;
import com.nerds.gamejam.ecs.component.*;
import com.nerds.gamejam.util.TextureReference;


import java.util.LinkedList;
import java.util.List;

import static com.nerds.gamejam.ecs.system.PlanetMapGeneratorSystem.PLANET_SPIRTE_SIZE;

public class PlanetFactory {

    private static final String PLANET_OUTLINE = "planet/outline.png";
    private static final String PLANET_BASE = "planet/base.png";
    private static final String PLANET_SHADOW = "planet/shadow.png";
    private static final Color SHADOW_COLOUR = new Color(0, 0, 0, 0.3f);
    private final PlanetNameFactory nameFactory = new PlanetNameFactory();

    public void createPlanet(World world, int orbitalRadius, int solarCenterX, int solarCenterY) {        Material baseMaterial = randomEnum(Material.class);
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

        float planetScale = GameJam.randomSeed.getRandomGenerator().nextFloat() + 0.5f;
        List<TextureReference> layers = createTextureReferences(baseMaterial, secondaryMaterial, landmass);

        // Set planet's initial position to a random location somewhere on its orbit
        double angle = GameJam.randomSeed.getRandomGenerator().nextDouble() * Math.PI * 2;
        int x = GameJam.orbitalCalculations.getPlanetXPosition(angle, orbitalRadius, planetScale);
        int y = GameJam.orbitalCalculations.getPlanetYPosition(angle, orbitalRadius, planetScale);

        double minOrbitalSpeed = 0.2;
        double maxOrbitalSpeed = 1.4;
        double orbitalSpeed = minOrbitalSpeed + GameJam.randomSeed.getRandomGenerator().nextDouble() * (maxOrbitalSpeed - minOrbitalSpeed);
        if (GameJam.randomSeed.getRandomGenerator().nextBoolean()) {
            orbitalSpeed = orbitalSpeed * -1;
        }

        Entity worldEntity = world.createEntity();
        worldEntity.edit()
                .add(new PositionComponent(x, y))
                .add(new BodyComponent(PLANET_SPIRTE_SIZE, PLANET_SPIRTE_SIZE))
                .add(new PlanetComponent(angle, orbitalRadius, orbitalSpeed))
                .add(new TextureReferenceComponent(layers))
                .add(new ScaleComponent(planetScale, planetScale))
                .add(new CircleComponent(solarCenterX, solarCenterY, orbitalRadius))
                .add(new FontComponent(nameFactory.generatePlanetName(), x - 10, y - 10));
    }

    private List<TextureReference> createTextureReferences(Material baseMaterial, Material secondaryMaterial, Landmass landmass) {
        List<TextureReference> layers = new LinkedList<>();
        layers.add(new TextureReference(PLANET_OUTLINE, Color.BLACK));
        layers.add(new TextureReference(PLANET_BASE, baseMaterial.getColor()));
        layers.add(new TextureReference(landmass.getFileName(), secondaryMaterial.getColor()));
        layers.add(new TextureReference(PLANET_SHADOW, SHADOW_COLOUR));
        return layers;
    }

    private boolean shouldBeDeathStar() {
        return GameJam.randomSeed.getRandomGenerator().nextInt(101) == 100;
    }

    public <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        int x = GameJam.randomSeed.getRandomGenerator().nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
}
