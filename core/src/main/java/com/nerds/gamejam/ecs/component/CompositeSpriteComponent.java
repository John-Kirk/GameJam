package com.nerds.gamejam.ecs.component;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

public class CompositeSpriteComponent extends Component {

    public Array<Sprite> sprites;

    public CompositeSpriteComponent() {
        //keep artemis happy
    }

    public CompositeSpriteComponent(Array<Sprite> sprites) {
        this.sprites = sprites;
    }

}
