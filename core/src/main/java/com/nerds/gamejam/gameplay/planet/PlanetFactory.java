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

    public void createPlanet(World world, int x, int y, int solarXCenter) {
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
        ColouredScaledSprite planetOutline = new ColouredScaledSprite(OUTLINE_TEXTURE, Color.BLACK, planetScale);
        sprites.add(planetOutline);
        sprites.add(new ColouredScaledSprite(BASE_TEXTURE, baseMaterial.getColor(), planetScale));
        sprites.add(new ColouredScaledSprite(LANDMASS_TEXTURES.get(landmass), secondaryMaterial.getColor(), planetScale));
        sprites.add(new ColouredScaledSprite(SHADOW_TEXTURE, SHADOW_COLOUR, planetScale));

        //Pythagoras WOOT!
        int solarDist = pythagoras(x - solarXCenter, y - solarXCenter);

        Entity worldEntity = world.createEntity();
        worldEntity.edit()
            .add(new PositionComponent(x, y))
            .add(RenderableComponent.INSTANCE)
            .add(new LandmassComponent(landmass))
            .add(new CompositeSpriteComponent(sprites))
            .add(new CircleComponent(solarXCenter, solarXCenter, (int) (solarDist + planetOutline.getWidth())))
            .add(new FontComponent(nameFactory.generatePlanetName(), x - 10, y - 10 ));
    }

    private int pythagoras(int a, int b) {
        return (int) Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
    }

    private boolean shouldBeDeathStar() {
        return GameJam.randomSeed.getRandomGenerator().nextInt(101) == 100;
    }

    public <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int x = GameJam.randomSeed.getRandomGenerator().nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

}
