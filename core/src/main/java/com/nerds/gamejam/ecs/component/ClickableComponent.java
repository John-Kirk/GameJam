package com.nerds.gamejam.ecs.component;

import com.artemis.Component;

public class ClickableComponent extends Component {

    private Clickable clickable;

    public ClickableComponent(Clickable clickable) {
        this.clickable = clickable;
    }

    public ClickableComponent() {
    }

    public Clickable getClickable() {
        return clickable;
    }
    public interface Clickable {
        boolean onClick(float x, float y, int button);
    }
}
