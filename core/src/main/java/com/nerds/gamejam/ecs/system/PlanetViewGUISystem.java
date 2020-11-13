package com.nerds.gamejam.ecs.system;

import com.artemis.BaseSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.nerds.gamejam.GameJam;
import com.nerds.gamejam.screen.MenuScreen;
import com.nerds.gamejam.util.InputUtil;

public class PlanetViewGUISystem extends BaseSystem {

    private final GameJam game;
    private final MenuScreen menuScreen;
    private Stage stage;

    public PlanetViewGUISystem(GameJam game, MenuScreen menuScreen) {
        this.game = game;
        this.menuScreen = menuScreen;
    }

    @Override
    protected void initialize() {
        this.stage = new Stage();
        this.stage.addActor(createPauseButton());
    }

    private Button createPauseButton() {
        Button button = new TextButton("||", GameJam.skin);
        button.setPosition(
              Gdx.graphics.getWidth() - button.getWidth() - 10,
              Gdx.graphics.getHeight() - button.getHeight() - 10
        );
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (InputUtil.INPUT_ALLOWED) {
                    game.setScreen(menuScreen);
                }
            }

        });
        return button;
    }

    @Override
    protected void processSystem() {
        Gdx.input.setInputProcessor(this.stage);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    protected void dispose() {
        stage.dispose();
    }

}
