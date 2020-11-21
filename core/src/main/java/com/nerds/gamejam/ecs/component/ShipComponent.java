package com.nerds.gamejam.ecs.component;

import com.artemis.Component;
import com.nerds.gamejam.gameplay.ship.Ship;

public class ShipComponent extends Component {

    public Ship ship;

    public ShipComponent(Ship ship) {
        this.ship = ship;
    }

    public ShipComponent() {
    }
}
