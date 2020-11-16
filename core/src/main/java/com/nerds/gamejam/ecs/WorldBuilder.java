package com.nerds.gamejam.ecs;

import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.nerds.gamejam.GameJam;
import com.nerds.gamejam.ecs.system.*;
import com.nerds.gamejam.gameplay.planet.PlanetFactory;
import com.nerds.gamejam.screen.MenuScreen;
import com.nerds.gamejam.util.CachingTextureLoader;
import com.nerds.gamejam.util.PositionUtil;

public class WorldBuilder {

    public static World build(GameJam game, MenuScreen menuScreen, CachingTextureLoader textureLoader) {
        Stage stage = new Stage();
        OrthographicCamera camera = new OrthographicCamera();
        ExtendViewport viewport = createViewport(camera);

        CameraSystem cameraSystem = new CameraSystem(camera);
        InputHandlerSystem inputHandlerSystem = new InputHandlerSystem(camera);

        WorldConfiguration worldConfiguration = new WorldConfigurationBuilder()
                .with(new GroupManager(),
                        new TagManager(),
                        new BootstrapSystem(textureLoader),
                        inputHandlerSystem,
                        new AnimationUpdateSystem(textureLoader),
                        new PlanetMapGeneratorSystem(new PlanetFactory(), textureLoader),
                        new BodyUpdateSystem(),
                        new PlanetSelectedSystem(new PositionUtil(camera, viewport)),
                        cameraSystem,
                        new ActorSystem(stage),
                        new GameIntroSystem(),
                        new MovementSystem(),
                        new RenderSystem(textureLoader, viewport),
                        new PlanetViewGUISystem(game, menuScreen, stage),
                        new MonsterControlSystem())
                .build();

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(inputHandlerSystem);
        inputMultiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(inputMultiplexer);

        return new World(worldConfiguration);
    }

    private static ExtendViewport createViewport(OrthographicCamera camera) {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        float worldWidth = GameJam.PLANET_VIEW_HEIGHT * w / h;
        int worldHeight = GameJam.PLANET_VIEW_HEIGHT;
        ExtendViewport viewport = new ExtendViewport(worldWidth, worldHeight, camera);
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        return viewport;
    }

}
