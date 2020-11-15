package com.nerds.gamejam.ecs.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nerds.gamejam.ecs.component.AnimationComponent;
import com.nerds.gamejam.ecs.component.TextureReferenceComponent;
import com.nerds.gamejam.util.CachingTextureLoader;
import com.nerds.gamejam.util.TextureReference;

public class AnimationUpdateSystem extends IteratingSystem {

    private ComponentMapper<AnimationComponent> animationComponentComponentMapper;
    private ComponentMapper<TextureReferenceComponent> textureReferenceComponentComponentMapper;

    private CachingTextureLoader textureLoader;

    public AnimationUpdateSystem(CachingTextureLoader textureLoader) {
        super(Aspect.all(AnimationComponent.class));
        this.textureLoader = textureLoader;
    }

    @Override
    protected void process(int entityId) {
        AnimationComponent animationComponent = animationComponentComponentMapper.get(entityId);
        TextureReferenceComponent textureReferenceComponent = textureReferenceComponentComponentMapper.get(entityId);

        if (textureReferenceComponent == null) {
            TextureReference textureReference = new TextureReference(animationComponent.animationReference, Color.WHITE);
            textureReferenceComponent = new TextureReferenceComponent(textureReference);
            world.edit(entityId).add(textureReferenceComponent);
        }

        animationComponent.elapsedTime = animationComponent.elapsedTime + Gdx.graphics.getDeltaTime();
        float elapsedTime = animationComponent.elapsedTime;
        Animation<TextureRegion> animation = textureLoader.getAnimation(animationComponent.animationReference);
        TextureRegion currentFrame = animation.getKeyFrame(elapsedTime);
        textureLoader.cacheTexture(animationComponent.animationReference, currentFrame);

        if (animation.getPlayMode() == Animation.PlayMode.NORMAL && animation.isAnimationFinished(elapsedTime) ) {
            world.edit(entityId).remove(animationComponent).remove(textureReferenceComponent);
        }
    }
}
