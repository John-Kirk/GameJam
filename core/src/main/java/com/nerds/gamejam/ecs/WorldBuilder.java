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
import com.nerds.gamejam.GameJam;
import com.nerds.gamejam.ecs.system.*;
import com.nerds.gamejam.gameplay.planet.PlanetFactory;
import com.nerds.gamejam.screen.MenuScreen;

public class WorldBuilder {

    public static World build(GameJam game, MenuScreen menuScreen) {
        OrthographicCamera orthographicCamera = new OrthographicCamera(512, GameJam.PLANET_VIEW_HEIGHT);
        Stage stage = new Stage();
        InputHandlerSystem inputHandlerSystem = new InputHandlerSystem(orthographicCamera);

        WorldConfiguration worldConfiguration = new WorldConfigurationBuilder()
                .with(new GroupManager(),
                        new TagManager(),
                        new BootstrapSystem(),
                        new PlanetMapGeneratorSystem(new PlanetFactory(), 64),
                        new CameraSystem(orthographicCamera),
                        new BodyUpdateSystem(),
                        new BackgroundRenderSystem(orthographicCamera),
                        new RenderSystem(orthographicCamera),
                        new PlanetViewGUISystem(game, menuScreen, new Stage()),
                        inputHandlerSystem)
                .build();

        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(inputHandlerSystem);
        inputMultiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(inputMultiplexer);

        return new World(worldConfiguration);
    }

}
