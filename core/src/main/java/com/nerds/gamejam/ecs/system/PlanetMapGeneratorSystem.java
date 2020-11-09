package com.nerds.gamejam.ecs.system;

import com.artemis.BaseSystem;
import com.nerds.gamejam.GameJam;
import com.nerds.gamejam.gameplay.planet.PlanetFactory;

public class PlanetMapGeneratorSystem extends BaseSystem {

    private final PlanetFactory planetFactory;
    private final int maxPlanets;

    public PlanetMapGeneratorSystem(PlanetFactory planetFactory, int maxPlanets) {
        this.planetFactory = planetFactory;
        this.maxPlanets = maxPlanets;
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
        for (int i = 0; i < GameJam.PLANET_VIEW_WIDTH; i += 256) {
            for (int j = 0; j < GameJam.PLANET_VIEW_HEIGHT; j += 256) {
                int randomX =
                      GameJam.randomSeed.getRandomGenerator().ints(i + 64, i + modifier).findFirst().getAsInt();
                int randomY =
                      GameJam.randomSeed.getRandomGenerator().ints(j + 64, j + modifier).findFirst().getAsInt();
               planetFactory.createPlanet(this.world, randomX, randomY);
               planetCount++;
            }
            if (planetCount >= maxPlanets) {
                return;
            }
        }
    }

    @Override
    protected void processSystem() {
        //not required, one-shot-system
    }

}
