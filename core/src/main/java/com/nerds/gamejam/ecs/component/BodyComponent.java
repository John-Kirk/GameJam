package com.nerds.gamejam.ecs.component;

import com.artemis.Component;

public class BodyComponent extends Component {

    public float width;
    public float height;

    public BodyComponent(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public BodyComponent() {
    }
}
