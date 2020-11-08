package com.nerds.gamejam.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.nerds.gamejam.GameJam;
import com.nerds.gamejam.translation.GameStrings;
import com.nerds.gamejam.actor.PlanetActor;
import com.nerds.gamejam.gameplay.map.PlanetMapFactory;
import com.nerds.gamejam.gameplay.map.PlanetMapStager;
import com.nerds.gamejam.gameplay.planet.PlanetFactory;

import java.util.Random;

public class GameScreen extends ScreenAdapter {

    private final GameJam game;
    private final Stage stage;
    private final Skin skin;
    private final Screen menuScreen;
    private Table table;
    private PlanetMapStager planetMapStager;

    public GameScreen(GameJam game, Stage stage, Skin skin, GameStrings strings) {
        this.game = game;
        this.stage = stage;
        this.skin = skin;
        this.menuScreen = new MenuScreen(game, stage, skin, this, strings);
        initialise();
    }

    private void initialise() {
        PlanetMapFactory planetMapFactory = new PlanetMapFactory(new PlanetFactory(), new Random());
        planetMapStager = new PlanetMapStager(planetMapFactory.createMap(13, 10, 3, 0));

        Table planetMapActors = planetMapStager.createPlanetMapActors();

        table = new Table();
        table.add(planetMapActors);
        table.setFillParent(true);
    }

    @Override
    public void show() {
        stage.addActor(createPauseButton());
        stage.addActor(table);
    }

    private Button createPauseButton() {
        Button button = new TextButton("||", skin);
        button.setPosition(
                Gdx.graphics.getWidth() - button.getWidth() - 10,
                Gdx.graphics.getHeight() - button.getHeight() - 10
        );
        button.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(menuScreen);
            }
        });
        return button;
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

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            stage.getViewport().getCamera().position.x += 25;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            stage.getViewport().getCamera().position.x -= 25;
        }
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
