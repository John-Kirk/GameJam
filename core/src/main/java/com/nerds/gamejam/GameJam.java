package com.nerds.gamejam;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.nerds.gamejam.screen.StartScreen;
import com.nerds.gamejam.translation.GameStrings;
import com.nerds.gamejam.util.RandomSeed;

public class GameJam extends Game {

	public static final int PLANET_VIEW_WIDTH = 1000;
	public static final int PLANET_VIEW_HEIGHT = 512;
	public static RandomSeed randomSeed;
	public static GameStrings gameStrings;
	public static Skin skin;

	@Override
	public void create () {
		randomSeed = new RandomSeed();
		gameStrings = new GameStrings();
		skin = new Skin(Gdx.files.getFileHandle("testskin/assets/uiskin.json", Files.FileType.Internal));
		setScreen(new StartScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		skin.dispose();
		this.screen.dispose();
	}
}
