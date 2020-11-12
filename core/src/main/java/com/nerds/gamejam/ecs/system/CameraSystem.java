package com.nerds.gamejam.ecs.system;

import com.artemis.BaseSystem;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.nerds.gamejam.GameJam;

@Wire
public class CameraSystem extends BaseSystem {

    OrthographicCamera camera;

    public CameraSystem(OrthographicCamera camera) {
        this.camera = camera;
    }

    @Override
    protected void initialize() {
        this.camera.zoom = 0.5f;
        setCameraBounds();
    }

    protected void resize(int width, int height) {
        camera.viewportHeight = height;
        camera.viewportWidth = width;
        setCameraBounds();
    }

    private void setCameraBounds() {
        camera.zoom = MathUtils.clamp(camera.zoom, 0.3f, 1f);
        float zoomedViewportWidth = camera.viewportWidth * camera.zoom;
        float zoomedViewportHeight = camera.viewportHeight * camera.zoom;
        float halfViewWidth = zoomedViewportWidth / 2f;
        float halfViewHeight = zoomedViewportHeight / 2f;
        float min = halfViewWidth;
        float max = GameJam.PLANET_VIEW_WIDTH - min;
        if (max <= min) {
            camera.position.x = GameJam.PLANET_VIEW_WIDTH / 2f;
        } else {
            camera.position.x = MathUtils.clamp(camera.position.x, min, max);
        }
        min = halfViewHeight;
        max = GameJam.PLANET_VIEW_HEIGHT - min;
        if (max <= min) {
            camera.position.y = GameJam.PLANET_VIEW_HEIGHT / 2f;
        } else {
            camera.position.y = MathUtils.clamp(camera.position.y, min, max);
        }
        this.camera.update();
    }

    @Override
    protected void processSystem() {
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            this.camera.position.x += 200 * world.getDelta();
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            this.camera.position.x -= 200 * world.getDelta();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            this.camera.position.y += 200 * world.getDelta();
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            this.camera.position.y -= 200 * world.getDelta();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            this.camera.zoom += 1f * world.getDelta();
        } else if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            this.camera.zoom -= 1f * world.getDelta();
        }
        setCameraBounds();
    }
}
