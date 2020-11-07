package com.nerds.gamejam.gameplay.planet;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlanetRenderer {

    private static final Texture base = new Texture("planet/base.png");
    private static final Texture shadow = new Texture("planet/shadow.png");
    private static final Texture outline = new Texture("planet/outline.png");

    private SpriteBatch spriteBatch;

    public PlanetRenderer(SpriteBatch spriteBatch) {
        this.spriteBatch = spriteBatch;
    }

    public void render(Planet planet, int x, int y, int width, int height) {
        spriteBatch.setColor(Color.BLACK);
        spriteBatch.draw(outline, x, y, width, height);
        spriteBatch.setColor(planet.getBaseMaterial().getColor());
        spriteBatch.draw(base, x, y, width, height);
        spriteBatch.setColor(planet.getSecondaryMaterial().getColor());
        spriteBatch.draw(new Texture(planet.getLandmass().getFileName()), x, y, width, height);
        spriteBatch.setColor(0, 0, 0, 0.5f);
        spriteBatch.draw(shadow, x, y, width, height);
    }
}
