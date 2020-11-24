package com.nerds.gamejam.ecs.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.utils.Array;
import com.nerds.gamejam.ecs.component.ActorComponent;
import com.nerds.gamejam.ecs.component.SceneComponent;

@Wire
public class SceneSystem extends IteratingSystem {

    private ComponentMapper<SceneComponent> sceneComponentComponentMapper;

    private SpeechSystem speechSystem;

    public SceneSystem() {
        super(Aspect.all(SceneComponent.class));
    }

    @Override
    protected void process(int entityId) {
        if (!speechSystem.dialogInProcess()) {
            SceneComponent sceneComponent = sceneComponentComponentMapper.get(entityId);

            if (sceneComponent.dialogs.size > 0) {
                world.createEntity().edit().add(sceneComponent.dialogs.removeIndex(0));
            } else {
                sceneComponent.background.actor.remove();
                world.delete(entityId);
                sceneComponent.endOfSceneCallback.execute();
            }
        }
    }

    @Override
    protected void inserted(int entityId) {
        SceneComponent sceneComponent = sceneComponentComponentMapper.get(entityId);
        world.createEntity().edit().add(sceneComponent.background);
        world.createEntity().edit().add(sceneComponent.dialogs.removeIndex(0));
    }

    public interface EndOfSceneCallback {
        void execute();
    }
}
