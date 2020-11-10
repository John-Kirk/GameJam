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
    private float halfViewWidth;
    private float halfViewHeight;
    private float viewportWidth;
    private float viewportHeight;

    @Override
    protected void initialize() {
        this.camera = new OrthographicCamera(512, 512);
        setCameraBounds();
        this.camera.position.x = halfViewWidth;
        this.camera.position.y = halfViewHeight;
        this.camera.zoom = 0.5f;
    }

    protected void resize(int width, int height) {
        camera.viewportHeight = height;
        camera.viewportWidth = width;
        setCameraBounds();
    }

    private void setCameraBounds() {
        viewportWidth = camera.viewportWidth * camera.zoom;
        viewportHeight = camera.viewportHeight * camera.zoom;
        halfViewWidth = viewportWidth / 2;
        halfViewHeight = viewportHeight / 2;
    }

    @Override
    protected void processSystem() {
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            this.camera.position.x += 100 * world.getDelta();
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            this.camera.position.x -= 100 * world.getDelta();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            this.camera.position.y += 100 * world.getDelta();
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            this.camera.position.y -= 100 * world.getDelta();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            this.camera.zoom = Math.min(this.camera.zoom + 0.1f, 1);
            setCameraBounds();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.E)) {
            this.camera.zoom = Math.max(this.camera.zoom - 0.1f, 0.3f);
            setCameraBounds();
        }

        float cameraLeft = camera.position.x - halfViewWidth;
        float cameraRight = camera.position.x + halfViewWidth;
        float cameraBottom = camera.position.y - halfViewHeight;
        float cameraTop = camera.position.y + halfViewHeight;

        if (GameJam.PLANET_VIEW_WIDTH < viewportWidth) {
            this.camera.position.x = halfViewWidth;
        } else if (cameraLeft < 0) {
            camera.position.x = halfViewWidth;
        } else if (cameraRight > GameJam.PLANET_VIEW_WIDTH) {
            this.camera.position.x = GameJam.PLANET_VIEW_WIDTH - halfViewWidth;
        }
        if (GameJam.PLANET_VIEW_HEIGHT < viewportHeight) {
            this.camera.position.y = halfViewHeight;
        } else if (cameraBottom < 0) {
            this.camera.position.y = halfViewHeight;
        } else if (cameraTop > GameJam.PLANET_VIEW_HEIGHT) {
            this.camera.position.y = GameJam.PLANET_VIEW_HEIGHT - halfViewHeight;
        }
        this.camera.update();
    }
}
