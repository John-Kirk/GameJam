package com.nerds.gamejam.ecs.system;

import com.artemis.Aspect;
import com.artemis.BaseEntitySystem;
import com.artemis.ComponentMapper;
import com.artemis.utils.IntBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector3;
import com.nerds.gamejam.ecs.component.BodyComponent;
import com.nerds.gamejam.ecs.component.ClickableComponent;
import com.nerds.gamejam.util.InputUtil;

public class InputHandlerSystem extends BaseEntitySystem implements InputProcessor {

    private ComponentMapper<BodyComponent> bodyComponentComponentMapper;
    private ComponentMapper<ClickableComponent> clickableComponentMapper;

    private final OrthographicCamera orthographicCamera;

    public InputHandlerSystem(OrthographicCamera orthographicCamera) {
        super(Aspect.all(BodyComponent.class, ClickableComponent.class));
        this.orthographicCamera = orthographicCamera;
    }

    @Override
    protected void processSystem() {
        //Do nothing
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (InputUtil.INPUT_ALLOWED) {
            IntBag entityIds = getEntityIds();

            Vector3 unproject = orthographicCamera.unproject(new Vector3(screenX, screenY, 0));
            float worldX = unproject.x;
            float worldY = unproject.y;

            for (int i = entityIds.size() - 1; i >= 0; i--) {
                int entityId = entityIds.get(i);
                BodyComponent bodyComponent = bodyComponentComponentMapper.get(entityId);
                ClickableComponent clickableComponent = clickableComponentMapper.get(entityId);

                Shape2D body = bodyComponent.physicalBody;

                if (body.contains(worldX, worldY)) {
                    if (clickableComponent.clickable.onClick(worldX, worldY, screenX, screenY, button)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (InputUtil.INPUT_ALLOWED && Gdx.input.isButtonPressed(Input.Buttons.RIGHT)) {
            float x = Gdx.input.getDeltaX();
            float y = Gdx.input.getDeltaY();

            orthographicCamera.translate(-x, y);

            return true;
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
