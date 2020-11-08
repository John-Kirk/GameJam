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
        this.setScale(planet.getScale());
    }

    @Override
    protected void scaleChanged() {
        this.setWidth(getWidth() * getScaleX());
        this.setHeight(getHeight() * getScaleY());
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        float width = getWidth();
        float height = getHeight();

        float x = getX();
        float y = getY();

        batch.setColor(Color.BLACK);
        batch.draw(outline, x, y, width, height);
        batch.setColor(planet.getBaseMaterial().getColor());
        batch.draw(base, x, y, width, height);
        batch.setColor(planet.getSecondaryMaterial().getColor());
        batch.draw(new Texture(planet.getLandmass().getFileName()), x, y, width, height);
        batch.setColor(0, 0, 0, 0.3f);
        batch.draw(shadow, x, y, width, height);
    }
}
