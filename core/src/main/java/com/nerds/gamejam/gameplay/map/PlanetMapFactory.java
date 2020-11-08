package com.nerds.gamejam.gameplay.map;

import com.badlogic.gdx.utils.Array;
import com.nerds.gamejam.gameplay.planet.Planet;
import com.nerds.gamejam.gameplay.planet.PlanetFactory;

import java.util.Random;

public class PlanetMapFactory {

    private final PlanetFactory planetFactory;
    private final Random random;

    public PlanetMapFactory(PlanetFactory planetFactory, Random random) {
        this.planetFactory = planetFactory;
        this.random = random;
    }

    public PlanetMap createMap(int maxUpcomingSystems, int minUpcomingSystems, int maxPlanetsPerSystem, int minPlanetsPerSystem) {
        System trailingSystem = createSystem(maxPlanetsPerSystem, 1);
        System startingSystem = createSystem(1, 1);
        Array<System> upcomingPlanets = createUpcomingPlanets(maxUpcomingSystems, minUpcomingSystems, maxPlanetsPerSystem, minPlanetsPerSystem);

        return new PlanetMap(trailingSystem, startingSystem, upcomingPlanets);
    }

    private Array<System> createUpcomingPlanets(int maxSystems, int minSystems, int maxPlanetsPerSystem, int minPlanetsPerSystem) {
        Array<System> systems = new Array<>();

        if (maxSystems > 0) {
            int numSystems = random.nextInt(maxSystems) + minSystems;

            for (int i = 0; i < numSystems; i++) {
                systems.add(createSystem(maxPlanetsPerSystem, minPlanetsPerSystem));
            }
        }

        return systems;
    }

    private System createSystem(int maxAmountPlanets, int minAmountPlanets) {
        Array<Planet> planets = new Array<>();

        if (maxAmountPlanets > 0) {
            int numPlanets = random.nextInt(maxAmountPlanets) + minAmountPlanets;

            for (int i = 0; i < numPlanets; i++) {
                planets.add(planetFactory.createPlanet());
            }
        }

        return new System(planets);
    }
}
