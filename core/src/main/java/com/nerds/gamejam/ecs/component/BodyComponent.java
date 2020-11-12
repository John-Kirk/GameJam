package com.nerds.gamejam.ecs.component;

import com.artemis.Component;
import com.badlogic.gdx.math.Shape2D;

public class BodyComponent extends Component {

    private Shape2D body;

    public BodyComponent(Shape2D body) {
        this.body = body;
    }

    public BodyComponent() {
    }

    public Shape2D getBody() {
        return body;
    }
}
