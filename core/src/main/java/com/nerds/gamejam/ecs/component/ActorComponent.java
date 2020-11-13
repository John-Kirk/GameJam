package com.nerds.gamejam.ecs.component;

import com.artemis.Component;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorComponent extends Component {

    private Actor actor;

    public ActorComponent(Actor actor) {
        this.actor = actor;
    }

    public ActorComponent() {
    }

    public Actor getActor() {
        return actor;
    }
}
