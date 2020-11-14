package com.nerds.gamejam.ecs.component;

import com.artemis.Component;

public class BodyComponent extends Component {

    private float width;
    private float height;

    public BodyComponent(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public BodyComponent() {
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
