package com.nerds.gamejam.ecs.component;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Color;

public class ColouredScaledSprite extends Sprite {

    public ColouredScaledSprite(Texture texture, Color color, float scale) {
        super(texture);
        this.setColor(color);
        this.setScale(scale);
    }

}
