package com.nerds.gamejam.ecs.component;

import com.artemis.Component;

public class CircleComponent extends Component {

    public int radius;
    public int y;
    public int x;

    public CircleComponent() {
        //keep artemis happy
    }

    public CircleComponent(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
}
