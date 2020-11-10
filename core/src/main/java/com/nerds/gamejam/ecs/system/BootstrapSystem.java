package com.nerds.gamejam.ecs.system;

import com.artemis.BaseSystem;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.nerds.gamejam.ecs.component.BackgroundComponent;
import com.nerds.gamejam.ecs.component.PositionComponent;
import com.nerds.gamejam.ecs.component.SpriteComponent;

public class BootstrapSystem extends BaseSystem {
    private static final Texture BACKGROUND_TEXTURE = new Texture("stars.png");

    {
        BACKGROUND_TEXTURE.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
    }

    @Override
    protected void initialize() {
        super.initialize();
        this.world.createEntity().edit()
                .add(new BackgroundComponent())
                .add(new PositionComponent(0, 0))
                .add(new SpriteComponent(new Sprite(BACKGROUND_TEXTURE)));
    }

    @Override
    protected void processSystem() {
        //nothing to process
    }
}
