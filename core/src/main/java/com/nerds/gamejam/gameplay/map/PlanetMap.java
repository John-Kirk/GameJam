package com.nerds.gamejam.gameplay.map;

import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.nerds.gamejam.gameplay.planet.Planet;

public class PlanetMap extends Table {

    private final Array<Planet> planets;

    public PlanetMap(Array<Planet> planets) {
        this.planets = planets;
    }

    public Array<Planet> getPlanets() {
        return planets;
    }
}
