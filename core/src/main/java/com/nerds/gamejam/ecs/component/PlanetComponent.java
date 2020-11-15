package com.nerds.gamejam.ecs.component;

import com.artemis.Component;

public class PlanetComponent extends Component {

    private double orbitAngle;
    private int orbitalDistance;
    private double orbitalSpeed;
    private double nextOrbitalAngle;

    public PlanetComponent() {
        //keep artemis happy
    }

    public PlanetComponent(double orbitAngle, int orbitalDistance, double orbitalSpeed) {
        this.orbitAngle = orbitAngle;
        this.orbitalDistance = orbitalDistance;
        this.orbitalSpeed = orbitalSpeed;
    }

    public double getOrbitAngle() {
        return orbitAngle;
    }

    public void setOrbitAngle(double orbitAngle) {
        this.orbitAngle = orbitAngle;
    }

    public int getOrbitalDistance() {
        return orbitalDistance;
    }

    public double getOrbitalSpeed() {
        return orbitalSpeed;
    }

    public double getNextOrbitalAngle() {
        return nextOrbitalAngle;
    }

    public void setNextOrbitalAngle() {
        this.nextOrbitalAngle = orbitAngle + (orbitalSpeed / 3);
    }
}
