package com.nerds.gamejam.ecs.component;

import com.artemis.Component;

public class SelectedPlanet extends Component {

    public String name;
    public float radius;
    public String desc;

    public SelectedPlanet(String name, float radius, String desc) {
        this.name = name;
        this.radius = radius;
        this.desc = desc;
    }

    public SelectedPlanet() {
    }
}
