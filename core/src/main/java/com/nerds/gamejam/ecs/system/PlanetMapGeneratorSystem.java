package com.nerds.gamejam.ecs.system;

import com.artemis.BaseSystem;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.nerds.gamejam.GameJam;
import com.nerds.gamejam.ecs.component.AnimationComponent;
import com.nerds.gamejam.ecs.component.BodyComponent;
import com.nerds.gamejam.ecs.component.PositionComponent;
import com.nerds.gamejam.gameplay.planet.PlanetFactory;
import com.nerds.gamejam.util.CachingTextureLoader;
import com.nerds.gamejam.util.TextureRegionFactory;

public class PlanetMapGeneratorSystem extends BaseSystem {

    private final PlanetFactory planetFactory;
    private final CachingTextureLoader textureLoader;
    private static final int MAX_PLANET_SIZE = 24;
    private static final Texture nebula = new Texture("animation/12_nebula_spritesheet.png");
    private static final Texture vortex = new Texture("animation/13_vortex_spritesheet.png");
    private static final Texture sun = new Texture("animation/16_sunburn_spritesheet.png");
    private final Array<TextureRegion> sunTextureRegionArray;

    private static final int ANIMATED_TEXTURE_SIZE = 192;

    public PlanetMapGeneratorSystem(PlanetFactory planetFactory, CachingTextureLoader textureLoader) {
        this.planetFactory = planetFactory;
        this.textureLoader = textureLoader;
        this.sunTextureRegionArray = TextureRegionFactory.createTextureRegionArray(sun, ANIMATED_TEXTURE_SIZE, ANIMATED_TEXTURE_SIZE, 38);
    }

    @Override
    protected void initialize() {
        createSolarSystem();
    }

    private void createSolarSystem() {
        int solarCenterX = GameJam.PLANET_VIEW_WIDTH / 2;
        int solarCenterY = GameJam.PLANET_VIEW_HEIGHT / 2;
        int orbitalDistance;
        int farRightOrbitX = solarCenterX + 50;
        do {
            planetFactory.createPlanet(this.world, farRightOrbitX, solarCenterX, solarCenterY);
            int xDistFromPrevious = GameJam.randomSeed.getRandomGenerator().nextInt(75) + 30;
            farRightOrbitX += xDistFromPrevious;
            orbitalDistance = farRightOrbitX - solarCenterX + MAX_PLANET_SIZE;
        } while (orbitalDistance * 2 < GameJam.PLANET_VIEW_WIDTH && orbitalDistance * 2 < GameJam.PLANET_VIEW_HEIGHT);
        createSun(solarCenterX, solarCenterY);
    }

    private void createSun(int solarCenterX, int solarCenterY) {
        Animation<TextureRegion> animation = new Animation<>(0.06f, sunTextureRegionArray, Animation.PlayMode.LOOP);
        String animationReference = textureLoader.cacheAnimation(animation);

        this.world.createEntity().edit().add(new PositionComponent(solarCenterX - ANIMATED_TEXTURE_SIZE/2, solarCenterY - ANIMATED_TEXTURE_SIZE/2))
            .add(new AnimationComponent(animationReference))
            .add(new BodyComponent(ANIMATED_TEXTURE_SIZE, ANIMATED_TEXTURE_SIZE));
    }

    @Override
    protected void processSystem() {
        //not required, one-shot-system
    }

    @Override
    protected void dispose() {
        super.dispose();
        nebula.dispose();
        vortex.dispose();
        sun.dispose();
    }

}
