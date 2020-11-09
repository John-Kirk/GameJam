package com.nerds.gamejam.ecs.component;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteComponent extends Component{

    public Sprite sprite;

    public SpriteComponent() {
        //keep artemis happy
    }
    public SpriteComponent(Sprite sprite) {
        this.sprite = sprite;
    }

}
