package com.nerds.gamejam.ecs.component;

import com.artemis.Component;

public class PositionComponent extends Component {

    public int x;
    public int y;

    public PositionComponent() {
        //keep artemis happy
    }

    public PositionComponent(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setPosition(int newX, int newY) {
        x = newX;
        y = newY;
    }
}
