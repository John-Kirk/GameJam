package com.nerds.gamejam.ecs.component;

import com.artemis.Component;

public class FontComponent extends Component {

    public String text;
    public int x;
    public int y;

    public FontComponent() {
        //keep artemis happy
    }

    public FontComponent(String text, int x, int y) {
        this.text = text;
        this.x = x;
        this.y = y;
    }

}
