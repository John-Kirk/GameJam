package com.nerds.gamejam.gameplay.map;

import com.nerds.gamejam.LibGdxTest;
import com.nerds.gamejam.gameplay.planet.PlanetFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Random;

public class PlanetMapFactoryTest extends LibGdxTest {

    PlanetMapFactory mapFactory;

    @Test
    public void testCreateMapWithNoUpcomingSystemsHasATrailingSystem() {
        mapFactory = new PlanetMapFactory(new PlanetFactory(), new Random());
        PlanetMap map = mapFactory.createMap(1, 0, 1, 0);
        assertNotNull(map.getTrailingSystem());
    }

    @Test
    public void testCreateMapWithNoUpcomingSystemsHasAHomeSystem() {
        mapFactory = new PlanetMapFactory(new PlanetFactory(), new Random());
        PlanetMap map = mapFactory.createMap(1, 0, 1, 0);
        assertNotNull(map.getHomeSystem());
    }
}
