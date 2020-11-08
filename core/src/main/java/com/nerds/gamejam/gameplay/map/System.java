package com.nerds.gamejam.gameplay.map;

import com.badlogic.gdx.utils.Array;
import com.nerds.gamejam.gameplay.planet.Planet;

public class System {

    private Array<Planet> planets;

    public System(Array<Planet> planets) {
        this.planets = planets;
    }

    public Array<Planet> getPlanets() {
        return planets;
    }
}
