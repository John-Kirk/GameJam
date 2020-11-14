package com.nerds.gamejam.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nerds.gamejam.ecs.component.AnimationComponent;
import com.nerds.gamejam.ecs.component.TextureReferenceComponent.TextureReference;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CachingTextureLoader implements TextureLoader {

    private Map<String, TextureRegion> textureMap;
    private Map<String, Animation<TextureRegion>> animationMap;

    public CachingTextureLoader() {
        this.textureMap = new HashMap<>();
        this.animationMap = new HashMap<>();
    }

    @Override
    public TextureRegion getTexture(TextureReference reference) {
        if (!textureMap.containsKey(reference.getReference())) {
            Texture texture = new Texture(reference.getReference());
            TextureWrap textureWrap = reference.getTextureWrap();
            if (textureWrap != null) {
                texture.setWrap(textureWrap, textureWrap);
            }

            textureMap.put(reference.getReference(), new TextureRegion(texture));
        }

        return textureMap.get(reference.getReference());
    }

    @Override
    public String cacheAnimation(Animation<TextureRegion> animation) {
        String uuid = UUID.randomUUID().toString();
        animationMap.put(uuid, animation);
        return uuid;
    }

    @Override
    public boolean updateAnimation(AnimationComponent animationComponent) {

        String animationReference = animationComponent.getAnimationReference();
        float elapsedTime = animationComponent.getElapsedTime();
        Animation<TextureRegion> animation = animationMap.get(animationReference);
        TextureRegion currentFrame = animation.getKeyFrame(elapsedTime);
        textureMap.put(animationReference, currentFrame);

        return animation.getPlayMode() == Animation.PlayMode.NORMAL &&
                animation.isAnimationFinished(elapsedTime);
    }
}
