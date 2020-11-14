package com.nerds.gamejam.ecs.component;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class AnimationComponent extends Component{

    private String animationReference;
    private float elapsedTime;

    public AnimationComponent() {
        //keep artemis happy
    }

    public AnimationComponent(String animationReference) {
        this.animationReference = animationReference;
    }

    public String getAnimationReference() {
        return animationReference;
    }

    public float getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(float elapsedTime) {
        this.elapsedTime = elapsedTime;
    }
}
