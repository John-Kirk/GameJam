package com.nerds.gamejam.ecs.component;

import com.artemis.Component;

public class VelocityComponent extends Component {
    public int velocityX;
    public int velocityY;

    public VelocityComponent() {
        //keep artemis happy
    }

    public VelocityComponent(int velocityX, int velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }
}
