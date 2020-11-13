package com.nerds.gamejam.gameplay.planet;

import com.artemis.Entity;
import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.nerds.gamejam.GameJam;
import com.nerds.gamejam.ecs.component.*;

import java.awt.*;
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

    public void createPlanet(World world, int x, int y) {
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

        Entity worldEntity = world.createEntity();
        String planetName = nameFactory.generatePlanetName();
        float radius = sprites.get(0).getWidth() / 2 * planetScale;
        Material finalBaseMaterial = baseMaterial;
        Material finalSecondaryMaterial = secondaryMaterial;
        Landmass finalLandmass = landmass;
        worldEntity.edit()
              .add(new PositionComponent(x, y))
              .add(RenderableComponent.INSTANCE)
              .add(new LandmassComponent(landmass))
              .add(new CompositeSpriteComponent(sprites))
              .add(new FontComponent(planetName, x - 10, y - 10 ))
              .add(new ClickableComponent((x1, y1, x2, y2, button) -> {
                  String desc = "A " + finalBaseMaterial.getDisplayName() + " planet, covered in " + finalSecondaryMaterial.getDisplayName() + " " + finalLandmass.getDisplayName() + " formations";
                  worldEntity.edit().add(new SelectedPlanet(planetName, x, y, radius, desc));
                  return true;
              }))
              .add(new BodyComponent(new Circle(x + radius, y + radius, radius)));
    }

    private boolean shouldBeDeathStar() {
        return GameJam.randomSeed.getRandomGenerator().nextInt(101) == 100;
    }

    public <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int x = GameJam.randomSeed.getRandomGenerator().nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

}
