package com.nerds.gamejam.gameplay.map;

import com.badlogic.gdx.utils.Array;
import com.nerds.gamejam.gameplay.planet.Planet;
import com.nerds.gamejam.gameplay.planet.PlanetFactory;

import java.util.concurrent.ThreadLocalRandom;

public class PlanetMapFactory {

    private static final int SYSTEM_WIDTH = 8192;
    private static final int SYSTEM_HEIGHT = 512;

    private final PlanetFactory planetFactory;

    public PlanetMapFactory(PlanetFactory planetFactory) {
        this.planetFactory = planetFactory;
    }

    public PlanetMap createMap(int maxPlanets) {
        Array<Planet> planets = generateNodes(SYSTEM_WIDTH, SYSTEM_HEIGHT, 256, 64, maxPlanets);
        return new PlanetMap(planets);
    }

    private Array<Planet> generateNodes(int areaWidth, int areaHeight, int boxSize, int minDistance, int maxPlanets) {
        Array<Planet> nodeList = new Array<>();
        for (int i = 0; i < areaWidth; i+= boxSize) {
            for (int j = 0; j < areaHeight; j+= boxSize) {
                int randomX = ThreadLocalRandom.current().nextInt(i + minDistance, i + boxSize - minDistance);
                int randomY = ThreadLocalRandom.current().nextInt(j + minDistance, j + boxSize - minDistance);
                nodeList.add(planetFactory.createPlanet(randomX, randomY));
            }

            if (nodeList.size >= maxPlanets) {
                return nodeList;
            }
        }

        return nodeList;
    }
}
