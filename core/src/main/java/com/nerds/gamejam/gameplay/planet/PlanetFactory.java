package com.nerds.gamejam.gameplay.planet;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.nerds.gamejam.GameJam;
import com.nerds.gamejam.ecs.component.CircleComponent;
import com.nerds.gamejam.ecs.component.ColouredScaledSprite;
import com.nerds.gamejam.ecs.component.CompositeSpriteComponent;
import com.nerds.gamejam.ecs.component.FontComponent;
import com.nerds.gamejam.ecs.component.LandmassComponent;
import com.nerds.gamejam.ecs.component.PositionComponent;
import com.nerds.gamejam.ecs.component.RenderableComponent;

import java.util.HashMap;
import java.util.Map;

public class PlanetFactory {

    private static final Texture BASE_TEXTURE = new Texture("planet/base.png");
    private static final Texture SHADOW_TEXTURE = new Texture("planet/shadow.png");
    private static final Texture OUTLINE_TEXTURE = new Texture("planet/outline.png");
    private static final Map<Landmass, Texture> LANDMASS_TEXTURES = createLandmassTextures();
    private static final Color SHADOW_COLOUR = new Color(0, 0, 0, 0.3f);
    private final PlanetNameFactory nameFactory = new PlanetNameFactory();

    private static Map<Landmass, Texture> createLandmassTextures() {
        Map<Landmass, Texture> textureMap = new HashMap<>();
        for (Landmass landmass : Landmass.values()) {
            textureMap.put(landmass, new Texture(landmass.getFileName()));
        }
        return textureMap;
    }

    public void createPlanet(World world, int farRightOrbitX, int solarCenterX, int solarCenterY) {
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

        float planetScale = GameJam.randomSeed.getRandomGenerator().nextFloat() + 0.5f;
        Array<Sprite> sprites = new Array<>(4);
        sprites.add(new ColouredScaledSprite(OUTLINE_TEXTURE, Color.BLACK, planetScale));
        sprites.add(new ColouredScaledSprite(BASE_TEXTURE, baseMaterial.getColor(), planetScale));
        sprites.add(new ColouredScaledSprite(LANDMASS_TEXTURES.get(landmass), secondaryMaterial.getColor(), planetScale));
        sprites.add(new ColouredScaledSprite(SHADOW_TEXTURE, SHADOW_COLOUR, planetScale));

        // Set planet's initial position to a random location somewhere on its orbit
        double angle = GameJam.randomSeed.getRandomGenerator().nextDouble() * Math.PI * 2;
        double radius = farRightOrbitX + sprites.get(0).getWidth() - solarCenterX;
        int x = solarCenterX + (int) (Math.cos(angle)*radius);
        int y = solarCenterY + (int) (Math.sin(angle)*radius);

        double minOrbitalSpeed = 0.2;
        double maxOrbitalSpeed = 1.4;
        double orbitalSpeed = minOrbitalSpeed + GameJam.randomSeed.getRandomGenerator().nextDouble() * (maxOrbitalSpeed - minOrbitalSpeed);

        Entity worldEntity = world.createEntity();
        worldEntity.edit()
            .add(new PositionComponent(x, y))
            .add(RenderableComponent.INSTANCE)
            .add(new LandmassComponent(landmass, angle, farRightOrbitX, orbitalSpeed))
            .add(new CompositeSpriteComponent(sprites))
            .add(new CircleComponent(solarCenterX, solarCenterY, (int) radius))
            .add(new FontComponent(nameFactory.generatePlanetName(), x - 10, y - 10 ));
    }

    private boolean shouldBeDeathStar() {
        return GameJam.randomSeed.getRandomGenerator().nextInt(101) == 100;
    }

    public <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int x = GameJam.randomSeed.getRandomGenerator().nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

}
