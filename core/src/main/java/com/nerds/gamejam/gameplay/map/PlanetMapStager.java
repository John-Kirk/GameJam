package com.nerds.gamejam.gameplay.map;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.nerds.gamejam.actor.PlanetActor;
import com.nerds.gamejam.gameplay.planet.Planet;

public class PlanetMapStager {

    private PlanetMap planetMap;

    public PlanetMapStager(PlanetMap planetMap) {
        this.planetMap = planetMap;
    }

    public Table createPlanetMapActors(Stage stage) {
        Table mapTable = new Table();

        planetMap.getPlanets().forEach(planet -> {
            PlanetActor planetActor = new PlanetActor(planet);
            planetActor.setPosition(planet.getX(), planet.getY());
            stage.addActor(planetActor);
        });

        return mapTable;
    }
}
