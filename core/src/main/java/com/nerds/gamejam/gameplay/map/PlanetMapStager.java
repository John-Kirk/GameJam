package com.nerds.gamejam.gameplay.map;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.nerds.gamejam.actor.PlanetActor;
import com.nerds.gamejam.gameplay.planet.Planet;

public class PlanetMapStager {

    private PlanetMap planetMap;

    public PlanetMapStager(PlanetMap planetMap) {
        this.planetMap = planetMap;
    }

    public Table createPlanetMapActors() {
        Table mapTable = new Table();
        addSystem(planetMap.getTrailingSystem(), mapTable);
        addSystem(planetMap.getHomeSystem(), mapTable);
        planetMap.getUpcomingSystems().forEach(system -> {
            addSystem(system, mapTable);
        });

        return mapTable;
    }

    private void addSystem(System system, Table mapTable) {
        Table systemTable = new Table();

        system.getPlanets().forEach(planet -> {
            PlanetActor planetActor = new PlanetActor(planet);
            systemTable.add(planetActor).expand().pad(192);
            systemTable.row();
        });
        mapTable.add(systemTable);
    }
}
