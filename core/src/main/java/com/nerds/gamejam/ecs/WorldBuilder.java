package com.nerds.gamejam.ecs;

import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.nerds.gamejam.GameJam;
import com.nerds.gamejam.ecs.system.*;
import com.nerds.gamejam.gameplay.planet.PlanetFactory;
import com.nerds.gamejam.screen.MenuScreen;
import com.nerds.gamejam.util.CachingTextureLoader;

public class WorldBuilder {

    public static World build(GameJam game, MenuScreen menuScreen, CachingTextureLoader textureLoader) {
        CameraSystem cameraSystem = new CameraSystem();
        WorldConfiguration worldConfiguration = new WorldConfigurationBuilder()
                .with(new GroupManager(),
                        new TagManager(),
                        new BootstrapSystem(textureLoader),
                        new PlanetMapGeneratorSystem(new PlanetFactory(), textureLoader),
                        cameraSystem,
                        new AnimationUpdateSystem(textureLoader),
                        new GameIntroSystem(),
                        new MovementSystem(),
                        new RenderSystem(textureLoader),
                        new PlanetViewGUISystem(game, menuScreen),
                        new MonsterControlSystem(),
                        new OrbitalSystem())
                .build();
        return new World(worldConfiguration);
    }

}
