package com.nerds.gamejam.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.nerds.gamejam.GameJam;
import com.nerds.gamejam.actor.AnimatedImage;
import com.nerds.gamejam.actor.PlanetActor;
import com.nerds.gamejam.gameplay.planet.PlanetFactory;

public class GameScreen extends ScreenAdapter {

    private final GameJam game;
    private final Stage stage;
    private final Skin skin;
    private PlanetFactory planetFactory;

    public GameScreen(GameJam game, Stage stage, Skin skin) {
        this.game = game;
        this.stage = stage;
        this.skin = skin;
        this.planetFactory = new PlanetFactory();
    }

    @Override
    public void show() {
        Table table = new Table();
        table.add(new PlanetActor(planetFactory.createPlanet())).pad(10);
        Array<TextureRegion> textureRegionArray = new Array<>();
        for (int i = 1; i < 6; i++) {
            TextureRegion textureRegion = new TextureRegion(new Texture("stripe/stripe_" + i + ".png"));
            textureRegionArray.add(textureRegion);
        }
        Animation<TextureRegion> textureRegionAnimation = new Animation<>(0.5f, textureRegionArray);
        AnimatedImage animatedImage = new AnimatedImage(textureRegionAnimation);
        animatedImage.setColor(Color.LIME);
        AnimatedImage animatedImage2 = new AnimatedImage(textureRegionAnimation);
        animatedImage2.setColor(Color.LIME);
        animatedImage2.rotateBy(20);
        animatedImage2.setOrigin(0, animatedImage2.getHeight() / 2);
        AnimatedImage animatedImage3 = new AnimatedImage(textureRegionAnimation);
        animatedImage3.setColor(Color.LIME);
        animatedImage3.rotateBy(-20);
        animatedImage3.setOrigin(0, animatedImage3.getHeight() / 2);

        Image image = new Image(new Texture("stripe/stripe_5.png"));
        image.rotateBy(20);
        image.setOrigin(0, image.getHeight() / 2);
        image.setColor(Color.LIME);
        Image image2 = new Image(new Texture("stripe/stripe_5.png"));
        image2.rotateBy(-20);
        image2.setOrigin(0, image2.getHeight() / 2);
        image2.setColor(Color.LIME);
        Image image3 = new Image(new Texture("stripe/stripe_5.png"));
        image3.setColor(Color.LIME);

        Stack stack = new Stack();
        stack.add(image);
        stack.add(image2);
        stack.add(image3);
        table.add(stack).fillX().expandX();

        Table verticalGroup = new Table();
        verticalGroup.add(new PlanetActor(planetFactory.createPlanet())).pad(5);
        verticalGroup.row();
        verticalGroup.add(new PlanetActor(planetFactory.createPlanet())).pad(5);
        verticalGroup.row();
        verticalGroup.add(new PlanetActor(planetFactory.createPlanet()));
        table.add(verticalGroup).pad(10);
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
