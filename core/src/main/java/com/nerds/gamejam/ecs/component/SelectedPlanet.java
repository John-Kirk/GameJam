package com.nerds.gamejam.ecs.component;

import com.artemis.Component;

public class SelectedPlanet extends Component {

    private String name;
    private float x;
    private float y;
    private float radius;
    private String desc;

    public SelectedPlanet(String name, float x, float y, float radius, String desc) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.desc = desc;
    }

    public SelectedPlanet() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
