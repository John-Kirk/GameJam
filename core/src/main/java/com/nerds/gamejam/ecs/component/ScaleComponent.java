package com.nerds.gamejam.ecs.component;

import com.artemis.Component;

public class ScaleComponent extends Component {

    public float x;
    public float y;

    public ScaleComponent(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public ScaleComponent() {
    }
}
