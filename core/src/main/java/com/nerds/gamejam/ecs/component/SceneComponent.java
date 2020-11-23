package com.nerds.gamejam.ecs.component;

import com.artemis.Component;
import com.badlogic.gdx.utils.Array;

public class SceneComponent extends Component {

    public Array<DialogComponent> dialogs;
    public ActorComponent background;

    public SceneComponent(Array<DialogComponent> dialogs, ActorComponent background) {
        this.dialogs = dialogs;
        this.background = background;
    }

    public SceneComponent() {
    }
}
