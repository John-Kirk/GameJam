package com.nerds.gamejam.util;

import com.nerds.gamejam.GameJam;

import static com.nerds.gamejam.ecs.system.PlanetMapGeneratorSystem.PLANET_SPIRTE_SIZE;

public class OrbitalCalculations {

    private int solarCenterX = GameJam.PLANET_VIEW_WIDTH / 2;
    private int solarCenterY = GameJam.PLANET_VIEW_HEIGHT / 2;

    public int getPlanetXPosition(double orbitalAngle, int orbitalDistance, float xScale) {
        return solarCenterX + (int) ((Math.cos(orbitalAngle) * orbitalDistance) - (PLANET_SPIRTE_SIZE / 2 * xScale));
    }

    public int getPlanetYPosition(double orbitalAngle, int orbitalDistance, float yScale) {
        return solarCenterY + (int) ((Math.sin(orbitalAngle) * orbitalDistance) - (PLANET_SPIRTE_SIZE / 2 * yScale));
    }
}
