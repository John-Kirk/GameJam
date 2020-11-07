package com.nerds.gamejam;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nerds.gamejam.gameplay.planet.Planet;
import com.nerds.gamejam.gameplay.planet.PlanetFactory;
import com.nerds.gamejam.gameplay.planet.PlanetRenderer;

public class GameJam extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	PlanetFactory planetFactory;
	PlanetRenderer planetRenderer;
	Planet planet;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		planetFactory = new PlanetFactory();
		planetRenderer = new PlanetRenderer(batch);
		planet = planetFactory.createPlanet();
	}

	@Override
	public void render () {
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			planet = planetFactory.createPlanet();
		}

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		planetRenderer.render(planet, Gdx.graphics.getWidth() / 2 - 64, Gdx.graphics.getHeight() / 2 - 64, 128, 128);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
