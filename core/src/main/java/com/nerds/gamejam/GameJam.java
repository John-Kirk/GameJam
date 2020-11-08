package com.nerds.gamejam;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.nerds.gamejam.gameplay.map.PlanetMapFactory;
import com.nerds.gamejam.gameplay.map.PlanetMapStager;
import com.nerds.gamejam.gameplay.planet.PlanetFactory;
import com.nerds.gamejam.gameplay.planet.PlanetFactory;
import com.nerds.gamejam.screen.GameScreen;
import com.nerds.gamejam.screen.StartScreen;
import com.nerds.gamejam.translation.GameStrings;
import com.nerds.gamejam.util.RandomSeed;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.injectors.ConstructorInjection;

public class GameJam extends Game {
	
	@Override
	public void create () {
		FileHandle fileHandle = Gdx.files.getFileHandle("testskin/assets/uiskin.json", Files.FileType.Internal);

		DefaultPicoContainer picoContainer = new DefaultPicoContainer(new ConstructorInjection());
		picoContainer.addComponent(new Stage())
			.addComponent(new Skin(fileHandle))
			.addComponent(this)
			.addComponent(StartScreen.class)
			.addComponent(GameScreen.class)
			.addComponent(GameStrings.class)
			.addComponent(RandomSeed.class)
			.addComponent(PlanetFactory.class)
			.addComponent(PlanetNameFactory.class);

		Gdx.input.setInputProcessor(picoContainer.getComponent(Stage.class));
		setScreen(picoContainer.getComponent(StartScreen.class));
	}
}
