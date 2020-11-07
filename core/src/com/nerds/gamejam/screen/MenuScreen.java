package com.nerds.gamejam.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.nerds.gamejam.GameJam;

public class MenuScreen extends ScreenAdapter {

    private final GameJam game;
    private final Stage stage;
    private final Skin skin;
    private final Screen gameScreen;

    public MenuScreen(GameJam game, Stage stage, Skin skin, Screen gameScreen) {
        this.game = game;
        this.stage = stage;
        this.skin = skin;
        this.gameScreen = gameScreen;
    }

    @Override
    public void show() {
        Table table = new Table();
        Button resume = new TextButton("Resume", skin);
        resume.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(gameScreen);
            }
        });
        table.addActor(resume);
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
    }

    @Override
    public void dispose() {
        this.game.dispose();
        this.stage.dispose();
        this.skin.dispose();
    }
}
