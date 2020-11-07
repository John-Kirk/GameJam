package com.nerds.gamejam.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public class ScrollingBackground extends Actor {

    private final Texture texture;

    float x;
    float y;
    float width;
    float height;
    int originX, originY,rotation,srcX,srcY;

    private float speed;
    private int scroll;

    public ScrollingBackground(Texture texture){
        this.texture = texture;
        this.texture.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
        this.width =  Gdx.graphics.getWidth();
        this.height = Gdx.graphics.getHeight();
    }

    public void setSpeed(float newSpeed){
        this.speed = newSpeed;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        scroll+=speed;
        batch.draw(texture, 0, 0, 0, 0, width, height, 1, 1, 0, scroll, 0, texture.getWidth(), texture.getHeight(), false, false);
    }
}
