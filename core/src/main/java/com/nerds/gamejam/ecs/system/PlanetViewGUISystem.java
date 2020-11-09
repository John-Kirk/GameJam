package com.nerds.gamejam.ecs.system;

import com.artemis.BaseSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.nerds.gamejam.GameJam;

public class PlanetViewGUISystem extends BaseSystem {

    private Stage stage;

    @Override
    protected void initialize() {
        stage = new Stage();
        stage.addActor(createPauseButton());
    }

    private Button createPauseButton() {
        Button button = new TextButton("||", GameJam.skin);
        button.setPosition(
              Gdx.graphics.getWidth() - button.getWidth() - 10,
              Gdx.graphics.getHeight() - button.getHeight() - 10
        );
        button.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                //perform pause, maybe greyscale everything via a pause system?
            }
        });
        return button;
    }

    @Override
    protected void processSystem() {
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    protected void dispose() {
        stage.dispose();
    }

}
