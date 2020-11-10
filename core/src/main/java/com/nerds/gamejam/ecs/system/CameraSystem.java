package com.nerds.gamejam.ecs.system;

import com.artemis.BaseSystem;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.nerds.gamejam.GameJam;

@Wire
public class CameraSystem extends BaseSystem {

    OrthographicCamera camera;
    private float lowerXBound;

    @Override
    protected void initialize() {
        float aspectRatio = (float)Gdx.graphics.getWidth() / (float) Gdx.graphics.getHeight();
        float worldHeight = GameJam.PLANET_VIEW_HEIGHT;
        float worldWidth = GameJam.PLANET_VIEW_HEIGHT * aspectRatio;

        this.camera = new OrthographicCamera(worldWidth, worldHeight);
        this.lowerXBound = camera.viewportWidth / 2;
        this.camera.position.x = lowerXBound;
        this.camera.position.y = camera.viewportHeight / 2f;
    }

    @Override
    protected void processSystem() {
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            this.camera.position.x += 200 * world.getDelta();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            this.camera.position.x -= 200 * world.getDelta();
        }

        if (this.camera.position.x < lowerXBound) {
            this.camera.position.x = lowerXBound;
        } else if (this.camera.position.x > GameJam.PLANET_VIEW_WIDTH) {
            this.camera.position.x = GameJam.PLANET_VIEW_WIDTH;
        }

        this.camera.update();
    }
}
