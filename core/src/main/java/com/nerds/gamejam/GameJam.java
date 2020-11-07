package com.nerds.gamejam;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.nerds.gamejam.screen.StartScreen;

public class GameJam extends Game {
	
	@Override
	public void create () {
		FileHandle fileHandle = Gdx.files.getFileHandle("testskin/assets/uiskin.json", Files.FileType.Internal);
		Stage stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		setScreen(new StartScreen(this, stage, new Skin(fileHandle)));
	}
}
