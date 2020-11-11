package com.nerds.gamejam.screen;

import com.artemis.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.nerds.gamejam.GameJam;
import com.nerds.gamejam.ecs.WorldBuilder;
import com.nerds.gamejam.ecs.system.RenderSystem;

public class GameScreen extends ScreenAdapter {

    private final GameJam game;
    private final World world;
    private final FPSLogger fpsLogger;

    public GameScreen(GameJam game) {
        this.game = game;
        this.world = WorldBuilder.build(game, new MenuScreen(game, this));
        this.fpsLogger = new FPSLogger();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.world.setDelta(delta);
        this.world.process();
        this.fpsLogger.log();
    }

    @Override
    public void resize(int width, int height) {
        RenderSystem renderSystem = world.getSystem(RenderSystem.class);
        renderSystem.resize(width, height);
    }

    @Override
    public void dispose() {
        this.world.dispose();
    }
}
