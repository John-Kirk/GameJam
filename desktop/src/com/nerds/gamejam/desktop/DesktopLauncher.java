package com.nerds.gamejam.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nerds.gamejam.GameJam;
import java.awt.Dimension;
import java.awt.Toolkit;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 4 * screenSize.width / 5;
		config.height = 4 * screenSize.height / 5;
		new LwjglApplication(new GameJam(), config);
	}
}
