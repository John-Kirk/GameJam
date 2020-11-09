package com.nerds.gamejam.ecs;

import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.nerds.gamejam.ecs.system.CameraSystem;
import com.nerds.gamejam.ecs.system.PlanetMapGeneratorSystem;
import com.nerds.gamejam.ecs.system.PlanetViewGUISystem;
import com.nerds.gamejam.ecs.system.RenderSystem;
import com.nerds.gamejam.gameplay.planet.PlanetFactory;

public class WorldBuilder {

    public static World build() {
        WorldConfiguration worldConfiguration = new WorldConfigurationBuilder()
              .with(new GroupManager(),
                new TagManager(),
                new PlanetMapGeneratorSystem(new PlanetFactory(), 64),
                new CameraSystem(),
                new RenderSystem(),
                new PlanetViewGUISystem())
              .build();
        return new World(worldConfiguration);
    }

}
