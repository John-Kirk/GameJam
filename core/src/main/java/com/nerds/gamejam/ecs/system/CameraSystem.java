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

    @Override
    protected void initialize() {
        this.camera = new OrthographicCamera(512, GameJam.PLANET_VIEW_HEIGHT);
    }

    @Override
    protected void processSystem() {
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            this.camera.position.x += 100 * world.getDelta();
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            this.camera.position.x -= 100 * world.getDelta();
        }
        if (this.camera.position.x < 0) {
            this.camera.position.x = 0;
        } else if (this.camera.position.x > 8192) {
            this.camera.position.x = 8192;
        }
        this.camera.update();
    }
}
