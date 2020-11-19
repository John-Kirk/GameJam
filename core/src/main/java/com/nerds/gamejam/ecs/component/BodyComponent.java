package com.nerds.gamejam.ecs.component;

import com.artemis.Component;
import com.badlogic.gdx.math.Shape2D;

public class BodyComponent extends Component {

    public float width;
    public float height;
    public Shape2D physicalBody;

    public BodyComponent(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public BodyComponent() {
    }
}
