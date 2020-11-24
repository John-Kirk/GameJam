package com.nerds.gamejam.ecs.component;

import com.artemis.Component;
import com.badlogic.gdx.utils.Array;
import com.nerds.gamejam.ecs.system.SceneSystem.EndOfSceneCallback;

public class SceneComponent extends Component {

    public Array<DialogComponent> dialogs;
    public ActorComponent background;
    public EndOfSceneCallback endOfSceneCallback;

    public SceneComponent(Array<DialogComponent> dialogs, ActorComponent background, EndOfSceneCallback endOfSceneCallback) {
        this.dialogs = dialogs;
        this.background = background;
        this.endOfSceneCallback = endOfSceneCallback;
    }

    public SceneComponent() {
    }
}
