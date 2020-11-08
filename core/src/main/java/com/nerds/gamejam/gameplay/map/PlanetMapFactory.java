package com.nerds.gamejam.gameplay.map;

import com.badlogic.gdx.utils.Array;
import com.nerds.gamejam.gameplay.planet.Planet;
import com.nerds.gamejam.gameplay.planet.PlanetFactory;
import com.nerds.gamejam.util.RandomSeed;

public class PlanetMapFactory {

    private static final int SYSTEM_WIDTH = 8192;
    private static final int SYSTEM_HEIGHT = 512;

    private final PlanetFactory planetFactory;
    private final RandomSeed randomSeed;

    public PlanetMapFactory(PlanetFactory planetFactory, RandomSeed randomSeed) {
        this.planetFactory = planetFactory;
        this.randomSeed = randomSeed;
    }

    public PlanetMap createMap(int maxPlanets) {
        Array<Planet> planets = generateNodes(SYSTEM_WIDTH, SYSTEM_HEIGHT, 256, 64, maxPlanets);
        return new PlanetMap(planets);
    }

    private Array<Planet> generateNodes(int areaWidth, int areaHeight, int boxSize, int minDistance, int maxPlanets) {
        Array<Planet> nodeList = new Array<>();
        for (int i = 0; i < areaWidth; i+= boxSize) {
            for (int j = 0; j < areaHeight; j+= boxSize) {
                int randomX = randomSeed.getRandomGenerator().ints(i + minDistance, i + boxSize - minDistance).findFirst().getAsInt();
                int randomY = randomSeed.getRandomGenerator().ints(j + minDistance, j + boxSize - minDistance).findFirst().getAsInt();
                nodeList.add(planetFactory.createPlanet(randomX, randomY));
            }

            if (nodeList.size >= maxPlanets) {
                return nodeList;
            }
        }

        return nodeList;
    }
}
