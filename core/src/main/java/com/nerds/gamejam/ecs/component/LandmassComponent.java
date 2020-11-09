package com.nerds.gamejam.ecs.component;

import com.artemis.Component;
import com.nerds.gamejam.gameplay.planet.Landmass;

public class LandmassComponent extends Component {

    public Landmass landmass;

    public LandmassComponent() {
        //keep artemis happy
    }

    public LandmassComponent(Landmass landmass) {
        this.landmass = landmass;
    }
}
