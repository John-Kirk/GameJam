package com.nerds.gamejam.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.nerds.gamejam.GameJam;
import com.nerds.gamejam.actor.PlanetActor;
import com.nerds.gamejam.gameplay.planet.PlanetFactory;

public class GameScreen extends ScreenAdapter {

    private final GameJam game;
    private final Stage stage;
    private final Skin skin;
    private PlanetFactory planetFactory;
    private PlanetActor planetActor;

    public GameScreen(GameJam game, Stage stage, Skin skin) {
        this.game = game;
        this.stage = stage;
        this.skin = skin;
        this.planetFactory = new PlanetFactory();
    }

    @Override
    public void show() {
        Table table = new Table();
        planetActor = new PlanetActor(planetFactory.createPlanet());
        planetActor.setWidth(128);
        planetActor.setHeight(128);

        stage.addListener(new InputListener() {
            @Override
            public boolean keyUp(InputEvent event, int keycode) {
                if (keycode == Input.Keys.SPACE) {
                    planetActor.setPlanet(planetFactory.createPlanet());
                }

                return false;
            }
        });

        table.add(planetActor).expand();
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
}
