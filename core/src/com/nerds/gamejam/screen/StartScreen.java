package com.nerds.gamejam.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.nerds.gamejam.GameJam;
import com.nerds.gamejam.actor.ScrollingBackground;

public class StartScreen extends ScreenAdapter {

    private final GameJam game;
    private final Stage stage;
    private final Skin skin;

    public StartScreen(GameJam game, Stage stage, Skin skin) {
        this.game = game;
        this.stage = stage;
        this.skin = skin;
    }

    @Override
    public void show() {
        Table table = new Table();
        Table innerTable = new Table();
        Button startButton = createStartButton();
        Button resumeButton = createResumeButton();
        Button quitButton = createExitButton();
        addButton(innerTable, startButton);
        addButton(innerTable, resumeButton);
        addButton(innerTable, quitButton);

        addPaddingRow(table, 3);
        table.add().expand();
        table.add(innerTable);
        table.add().expand();
        table.row();
        addPaddingRow(table, 3);
        table.setFillParent(true);

        ScrollingBackground scrollingBackground = new ScrollingBackground(new Texture("stars.png"));
        scrollingBackground.setSpeed(1f);

        Stack stack = new Stack();
        stack.add(scrollingBackground);
        stack.add(table);
        stack.setFillParent(true);

        stage.addActor(stack);
    }

    private void addPaddingRow(Table table, int columns) {
        for (int i = 0; i < columns; i++) {
            table.add().expand();
        }
        table.row();
    }

    private void addButton(Table table, Button button) {
        table.add(button).expand().pad(10);
        table.row();
    }

    private Button createStartButton() {
        Button startButton = new TextButton("Start New Game", skin);
        startButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameScreen(game, stage, skin));
            }
        });
        return startButton;
    }

    private Button createResumeButton() {
        Button resumeButton = new TextButton("Continue Game", skin);
        resumeButton.setTouchable(Touchable.disabled);
        return resumeButton;
    }

    private Button createExitButton() {
        Button quitButton = new TextButton("Quit Game", skin);
        quitButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
            }
        });
        return quitButton;
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor( 0, 0, 0, 0 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
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
        stage.dispose();
    }
}
