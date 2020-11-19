package com.nerds.gamejam.ecs.component;

import com.artemis.Component;
import com.nerds.gamejam.util.Clickable;

public class ClickableComponent extends Component {

    public Clickable clickable;

    public ClickableComponent(Clickable clickable) {
        this.clickable = clickable;
    }

    public ClickableComponent() {
    }
}
