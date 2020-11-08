package com.nerds.gamejam.gameplay.map;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;

public class PlanetMap extends Table {

    private final System trailingSystem;
    private final System homeSystem;
    private final Array<System> upcomingSystems;

    public PlanetMap(System trailingSystem, System homeSystem, Array<System> upcomingSystems) {
        this.trailingSystem = trailingSystem;
        this.homeSystem = homeSystem;
        this.upcomingSystems = upcomingSystems;
    }

    public System getTrailingSystem() {
        return trailingSystem;
    }

    public System getHomeSystem() {
        return homeSystem;
    }

    public Array<System> getUpcomingSystems() {
        return upcomingSystems;
    }
}
