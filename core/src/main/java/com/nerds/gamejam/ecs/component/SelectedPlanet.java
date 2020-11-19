package com.nerds.gamejam.ecs.component;

import com.artemis.Component;

public class SelectedPlanet extends Component {

    public String name;
    public float x;
    public float y;
    public float radius;
    public String desc;

    public SelectedPlanet(String name, float x, float y, float radius, String desc) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.desc = desc;
    }

    public SelectedPlanet() {
    }
}
