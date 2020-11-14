package com.nerds.gamejam.ecs.system;

import com.artemis.BaseSystem;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.nerds.gamejam.GameJam;
import com.nerds.gamejam.ecs.component.AnimationComponent;
import com.nerds.gamejam.ecs.component.PositionComponent;
import com.nerds.gamejam.gameplay.planet.PlanetFactory;
import com.nerds.gamejam.util.TextureRegionFactory;

public class PlanetMapGeneratorSystem extends BaseSystem {

    private final PlanetFactory planetFactory;
    private final int maxPlanets;
    private static final Texture nebula = new Texture("animation/12_nebula_spritesheet.png");
    private static final Texture vortex = new Texture("animation/13_vortex_spritesheet.png");
    private static final Texture sun = new Texture("animation/16_sunburn_spritesheet.png");
    private final Array<TextureRegion> nebulaTextureRegionArray;
    private final Array<TextureRegion> vortexTextureRegionArray;
    private final Array<TextureRegion> sunTextureRegionArray;

    public PlanetMapGeneratorSystem(PlanetFactory planetFactory, int maxPlanets) {
        this.planetFactory = planetFactory;
        this.maxPlanets = maxPlanets;
        this.nebulaTextureRegionArray = TextureRegionFactory.createTextureRegionArray(nebula, 192, 192, 38);
        this.vortexTextureRegionArray = TextureRegionFactory.createTextureRegionArray(vortex, 192, 192, 38);
        this.sunTextureRegionArray = TextureRegionFactory.createTextureRegionArray(sun, 192, 192, 38);
    }

    @Override
    protected void initialize() {
        createMap();
    }

    private void createMap() {
        int boxSize = 256;
        int minDistance = 64;
        int modifier = boxSize - minDistance;
        int planetCount = 0;
        for (int i = 0; i <= GameJam.PLANET_VIEW_WIDTH - (minDistance + modifier); i += boxSize) {
            for (int j = 0; j < GameJam.PLANET_VIEW_HEIGHT; j += boxSize) {
                int randomX =
                      GameJam.randomSeed.getRandomGenerator().ints(i + minDistance, i + modifier).findFirst().getAsInt();
                int randomY =
                      GameJam.randomSeed.getRandomGenerator().ints(j + minDistance, j + modifier).findFirst().getAsInt();
                randomY = MathUtils.clamp(randomY, minDistance, GameJam.PLANET_VIEW_HEIGHT);
                planetFactory.createPlanet(this.world, randomX, randomY);
                planetCount++;
            }
            if (planetCount >= maxPlanets) {
                //createBackgroundAnimationObjects();
                return;
            }
        }
    }

    private void createBackgroundAnimationObjects() {
//        this.world.createEntity().edit().add(new PositionComponent(20, 300))
//              .add(new AnimationComponent(nebulaTextureRegionArray, 0.06f, Animation.PlayMode.LOOP))
//              .add(RenderableComponent.INSTANCE);
//        this.world.createEntity().edit().add(new PositionComponent(125, GameJam.PLANET_VIEW_HEIGHT/4))
//              .add(new AnimationComponent(vortexTextureRegionArray, 0.06f, Animation.PlayMode.LOOP))
//              .add(RenderableComponent.INSTANCE);
//        this.world.createEntity().edit().add(new PositionComponent(290, 200))
//              .add(new AnimationComponent(sunTextureRegionArray, 0.06f, Animation.PlayMode.LOOP))
//              .add(RenderableComponent.INSTANCE);
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
