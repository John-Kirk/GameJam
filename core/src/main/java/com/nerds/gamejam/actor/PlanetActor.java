package com.nerds.gamejam.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.nerds.gamejam.gameplay.planet.Landmass;
import com.nerds.gamejam.gameplay.planet.Planet;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class PlanetActor extends Actor {

    private static final Texture base = new Texture("planet/base.png");
    private static final Texture shadow = new Texture("planet/shadow.png");
    private static final Texture outline = new Texture("planet/outline.png");
    private static final Map<Landmass, Texture> landmassTextures = createLandmassTextures();

    private static Map<Landmass, Texture> createLandmassTextures() {
        Map<Landmass, Texture> textureMap = new HashMap<>();

        for (Landmass landmass : Landmass.values()) {
            textureMap.put(landmass, new Texture(landmass.getFileName()));
        }

        return textureMap;
    }

    private Planet planet;

    public PlanetActor(Planet planet) {
        this.planet = planet;
        this.setPosition(planet.getX(), planet.getY());
        this.setWidth(planet.getWidth());
        this.setHeight(planet.getHeight());
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
        batch.draw(landmassTextures.get(planet.getLandmass()), x, y, width, height);
        batch.setColor(0, 0, 0, 0.3f);
        batch.draw(shadow, x, y, width, height);
    }
}
