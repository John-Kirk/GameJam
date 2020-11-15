package com.nerds.gamejam.ecs.system;

import com.artemis.Aspect;
import com.artemis.BaseEntitySystem;
import com.artemis.ComponentMapper;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.nerds.gamejam.ecs.component.ActorComponent;


public class ActorSystem extends BaseEntitySystem {

    private ComponentMapper<ActorComponent> actorComponentComponentMapper;

    private Stage stage;

    public ActorSystem(Stage stage) {
        super(Aspect.all(ActorComponent.class));
        this.stage = stage;
    }

    @Override
    protected void processSystem() {
        //Do nothing
    }

    @Override
    protected void inserted(int entityId) {
        ActorComponent actorComponent = actorComponentComponentMapper.get(entityId);
        stage.addActor(actorComponent.actor);
    }

    @Override
    protected void removed(int entityId) {
        ActorComponent actorComponent = actorComponentComponentMapper.get(entityId);
        actorComponent.actor.remove();
    }
}
