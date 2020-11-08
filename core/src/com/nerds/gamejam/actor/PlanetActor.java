package com.nerds.gamejam.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.nerds.gamejam.gameplay.planet.Planet;

public class PlanetActor extends Actor {

    private static final Texture base = new Texture("planet/base.png");
    private static final Texture shadow = new Texture("planet/shadow.png");
    private static final Texture outline = new Texture("planet/outline.png");

    private Planet planet;

    public PlanetActor(Planet planet) {
        this.planet = planet;
        this.setWidth(128);
        this.setHeight(128);
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        float scale = planet.getScale();
        float width = getWidth();
        float height = getHeight();

        float scaledWidth = width * scale;
        float scaledHeight = height * scale;

        float deltaX = width - scaledWidth;
        float deltaY = height - scaledWidth;

        float x = getX() + deltaX/2;
        float y = getY() + deltaY/2;

        batch.setColor(Color.BLACK);
        batch.draw(outline, x, y, scaledWidth, scaledHeight);
        batch.setColor(planet.getBaseMaterial().getColor());
        batch.draw(base, x, y, scaledWidth, scaledHeight);
        batch.setColor(planet.getSecondaryMaterial().getColor());
        batch.draw(new Texture(planet.getLandmass().getFileName()), x, y, scaledWidth, scaledHeight);
        batch.setColor(0, 0, 0, 0.3f);
        batch.draw(shadow, x, y, scaledWidth, scaledHeight);
    }
}
