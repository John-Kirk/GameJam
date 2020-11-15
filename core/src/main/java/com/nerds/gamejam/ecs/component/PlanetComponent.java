package com.nerds.gamejam.ecs.component;

import com.artemis.Component;

public class PlanetComponent extends Component {

    public boolean orbitalDirection;
    public double orbitalAngle;
    public int orbitalDistance;
    public double orbitalSpeed;
    public double nextOrbitalAngle;

    public PlanetComponent() {
        //keep artemis happy
    }

    public PlanetComponent(double orbitalAngle, int orbitalDistance, double orbitalSpeed) {
        this.orbitalAngle = orbitalAngle;
        this.orbitalDistance = orbitalDistance;
        this.orbitalSpeed = orbitalSpeed;
        this.orbitalDirection = orbitalSpeed > 0;
        this.nextOrbitalAngle = orbitalAngle;
    }

    public void setNextOrbitalAngle() {
        this.nextOrbitalAngle = orbitalAngle + (orbitalSpeed / 3);
    }
}
