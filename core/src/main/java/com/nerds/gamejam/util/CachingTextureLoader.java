package com.nerds.gamejam.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nerds.gamejam.ecs.component.AnimationComponent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CachingTextureLoader {

    private Map<String, TextureRegion> textureMap;
    private Map<String, Animation<TextureRegion>> animationMap;

    public CachingTextureLoader() {
        this.textureMap = new HashMap<>();
        this.animationMap = new HashMap<>();
    }

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

    public void cacheTexture(String reference, TextureRegion textureRegion) {
        textureMap.put(reference, textureRegion);
    }

    public String cacheAnimation(Animation<TextureRegion> animation) {
        String uuid = UUID.randomUUID().toString();
        animationMap.put(uuid, animation);
        return uuid;
    }

    public Animation<TextureRegion> getAnimation(String reference) {
        return animationMap.get(reference);
    }

    public void dispose() {
        textureMap.forEach((s, textureRegion) -> textureRegion.getTexture().dispose());
        animationMap.forEach((s, textureRegionAnimation) ->
                Arrays.stream(textureRegionAnimation.getKeyFrames()).forEach(textureRegion ->
                        textureRegion.getTexture().dispose()));
    }
}
