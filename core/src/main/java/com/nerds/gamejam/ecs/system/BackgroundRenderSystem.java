package com.nerds.gamejam.ecs.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nerds.gamejam.GameJam;
import com.nerds.gamejam.ecs.component.BackgroundComponent;
import com.nerds.gamejam.ecs.component.SpriteComponent;

@Wire
public class BackgroundRenderSystem extends IteratingSystem {

    private final OrthographicCamera orthographicCamera;
    private ComponentMapper<SpriteComponent> spriteMapper;
    private CameraSystem cameraSystem;
    private Batch batch;

    public BackgroundRenderSystem(OrthographicCamera orthographicCamera) {
        super(Aspect.all(BackgroundComponent.class));
        this.orthographicCamera = orthographicCamera;
    }

    @Override
    protected void initialize() {
        this.batch = new SpriteBatch();
    }

    @Override
    protected void begin() {
        this.batch.setProjectionMatrix(orthographicCamera.combined);
        this.batch.begin();
    }

    @Override
    protected void process(int e) {
        SpriteComponent spriteComponent = spriteMapper.get(e);
        if (spriteComponent != null) {
            this.batch.draw(spriteComponent.sprite.getTexture(), 0, 0, 0, 0,
                    GameJam.PLANET_VIEW_WIDTH, GameJam.PLANET_VIEW_HEIGHT);
        }
    }

    @Override
    protected void end() {
        this.batch.end();
    }

    @Override
    protected void dispose() {
        this.batch.dispose();
    }

}
