package com.nerds.gamejam.ecs.system;

import com.artemis.BaseSystem;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.nerds.gamejam.GameJam;
import com.nerds.gamejam.util.InputUtil;

@Wire
public class CameraSystem extends BaseSystem {

    OrthographicCamera camera;
    ScalingViewport scalingViewport;

    @Override
    protected void initialize() {
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false);
        this.camera.zoom = 1;
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        scalingViewport = new FillViewport(GameJam.PLANET_VIEW_HEIGHT * w / h, GameJam.PLANET_VIEW_HEIGHT, camera);
        scalingViewport.setScreenBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        scalingViewport.apply();
        camera.position.add(GameJam.PLANET_VIEW_HEIGHT / -2f * w / h, GameJam.PLANET_VIEW_HEIGHT / -2f, 0);
    }

    protected void resize(int width, int height) {
        scalingViewport.setScreenBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        scalingViewport.apply();
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
        this.scalingViewport.apply();
        this.camera.update();
    }

    @Override
    protected void processSystem() {
        if (InputUtil.isKeyPressed(Input.Keys.D)) {
            this.camera.position.x += 200 * world.getDelta();
        } else if (InputUtil.isKeyPressed(Input.Keys.A)) {
            this.camera.position.x -= 200 * world.getDelta();
        }
        if (InputUtil.isKeyPressed(Input.Keys.W)) {
            this.camera.position.y += 200 * world.getDelta();
        } else if (InputUtil.isKeyPressed(Input.Keys.S)) {
            this.camera.position.y -= 200 * world.getDelta();
        }
        if (InputUtil.isKeyPressed(Input.Keys.Q)) {
            this.camera.zoom += 1f * world.getDelta();
        } else if (InputUtil.isKeyPressed(Input.Keys.E)) {
            this.camera.zoom -= 1f * world.getDelta();
        }
        if (InputUtil.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        setCameraBounds();
    }
}
