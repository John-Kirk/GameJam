package com.nerds.gamejam.ecs.component;

import com.artemis.Component;

public class ScaleComponent extends Component {

    private float x;
    private float y;

    public ScaleComponent(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public ScaleComponent() {
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
