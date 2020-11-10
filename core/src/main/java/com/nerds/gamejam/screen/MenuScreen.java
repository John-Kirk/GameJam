package com.nerds.gamejam.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.nerds.gamejam.GameJam;

public class MenuScreen extends ScreenAdapter {

    private final GameJam game;
    private final Stage stage;
    private final Screen gameScreen;

    public MenuScreen(GameJam game, Screen gameScreen) {
        this.game = game;
        this.stage = new Stage();
        this.gameScreen = gameScreen;
    }

    @Override
    public void show() {
        Table table = new Table();
        Button resume = new TextButton(GameJam.gameStrings.get("resumeButton"), GameJam.skin);
        resume.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(gameScreen);
            }
        });
        Button restart = new TextButton(GameJam.gameStrings.get("restartButton"), GameJam.skin);
        table.add(resume);
        table.setFillParent(true);
        stage.addActor(table);
        Gdx.input.setInputProcessor(this.stage);
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
        this.gameScreen.dispose();
        this.stage.dispose();
    }
}
