package com.nerds.gamejam.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.nerds.gamejam.GameJam;
import com.nerds.gamejam.translation.GameStrings;

public class MenuScreen extends ScreenAdapter {

    private final GameJam game;
    private final Stage stage;
    private final Skin skin;
    private final Screen gameScreen;
    private final GameStrings strings;

    public MenuScreen(GameJam game, Stage stage, Skin skin, Screen gameScreen, GameStrings strings) {
        this.game = game;
        this.stage = stage;
        this.skin = skin;
        this.gameScreen = gameScreen;
        this.strings = strings;
    }

    @Override
    public void show() {
        Table table = new Table();
        Button resume = new TextButton(strings.get("resumeButton"), skin);
        resume.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(gameScreen);
            }
        });
        Button restart = new TextButton(strings.get("restartButton"), skin);
        table.add(resume);
        table.setFillParent(true);
        stage.addActor(table);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void hide() {
        stage.getActors().forEach(Actor::remove);
        stage.clear();
    }

    @Override
    public void dispose() {
        this.game.dispose();
        this.stage.dispose();
        this.skin.dispose();
    }
}
