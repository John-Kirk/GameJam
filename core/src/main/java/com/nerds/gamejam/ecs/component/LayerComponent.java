package com.nerds.gamejam.ecs.component;

import com.artemis.Component;

public class LayerComponent extends Component {

    public static int BACKGROUND = 0;
    public static int ORBIT_MARKERS = 1;
    public static int PLANETS = 2;
    public static int MONSTER = 3;

    public int layer;

    public LayerComponent() {
        //keep artemis happy
    }

    public LayerComponent(int layer) {
        this.layer = layer;
    }

}
