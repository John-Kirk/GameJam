package com.nerds.gamejam.ecs.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.nerds.gamejam.ecs.component.AnimationComponent;
import com.nerds.gamejam.ecs.component.TextureReferenceComponent;
import com.nerds.gamejam.ecs.component.TextureReferenceComponent.TextureReference;
import com.nerds.gamejam.util.TextureLoader;

import java.util.Collections;

public class AnimationUpdateSystem extends IteratingSystem {

    private ComponentMapper<AnimationComponent> animationComponentComponentMapper;
    private ComponentMapper<TextureReferenceComponent> textureReferenceComponentComponentMapper;

    private TextureLoader textureLoader;

    public AnimationUpdateSystem(TextureLoader textureLoader) {
        super(Aspect.all(AnimationComponent.class));
        this.textureLoader = textureLoader;
    }

    @Override
    protected void process(int entityId) {
        AnimationComponent animationComponent = animationComponentComponentMapper.get(entityId);
        TextureReferenceComponent textureReferenceComponent = textureReferenceComponentComponentMapper.get(entityId);

        if (textureReferenceComponent == null) {
            TextureReference textureReference = new TextureReference(animationComponent.getAnimationReference(), Color.WHITE);
            textureReferenceComponent = new TextureReferenceComponent(Collections.singletonList(textureReference));
            world.edit(entityId).add(textureReferenceComponent);
        }

        animationComponent.setElapsedTime(animationComponent.getElapsedTime() + Gdx.graphics.getDeltaTime());
        boolean finished = textureLoader.updateAnimation(animationComponent);

        if (finished) {
            world.edit(entityId).remove(animationComponent).remove(textureReferenceComponent);
        }
    }
}
