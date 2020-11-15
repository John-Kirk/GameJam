package com.nerds.gamejam.ecs.component;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class AnimationComponent extends Component{

    public String animationReference;
    public float elapsedTime;

    public AnimationComponent() {
        //keep artemis happy
    }

    public AnimationComponent(String animationReference) {
        this.animationReference = animationReference;
    }
}
