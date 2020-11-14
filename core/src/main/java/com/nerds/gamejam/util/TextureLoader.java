package com.nerds.gamejam.util;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nerds.gamejam.ecs.component.AnimationComponent;
import com.nerds.gamejam.ecs.component.TextureReferenceComponent.TextureReference;

public interface TextureLoader {

    TextureRegion getTexture(TextureReference reference);
    String cacheAnimation(Animation<TextureRegion> animation);
    boolean updateAnimation(AnimationComponent animationComponent);

}
