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
        this.camera = new OrthographicCamera(512, GameJam.PLANET_VIEW_HEIGHT);
        this.lowerXBound = camera.viewportWidth / 2;
        this.camera.position.x = lowerXBound;
        this.camera.position.y = camera.viewportHeight / 2;
    }

    @Override
    protected void processSystem() {
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            this.camera.position.x += 100 * world.getDelta();
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            this.camera.position.x -= 100 * world.getDelta();
        }
        if (this.camera.position.x < lowerXBound) {
            this.camera.position.x = lowerXBound;
        } else if (this.camera.position.x > GameJam.PLANET_VIEW_WIDTH) {
            this.camera.position.x = GameJam.PLANET_VIEW_WIDTH;
        }
        this.camera.update();
    }
}
