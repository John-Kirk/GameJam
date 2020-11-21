package com.nerds.gamejam.gameplay.character;

import com.nerds.gamejam.gameplay.character.stat.StatBlock;

public class CrewMember {

    private String name;
    private StatBlock statBlock;
    private String spriteReference;
    private String portraitReference;
    private String animationReference;

    public CrewMember(String name, StatBlock statBlock) {
        this.name = name;
        this.statBlock = statBlock;
        this.spriteReference = "characters/" + name + ".png";
        this.portraitReference = "characters/" + name + "portrait.png";
        this.animationReference = "characters/" + name + "sprite.png";
    }

    public String getName() {
        return name;
    }

    public StatBlock getStatBlock() {
        return statBlock;
    }

    public String getSpriteReference() {
        return spriteReference;
    }

    public String getPortraitReference() {
        return portraitReference;
    }

    public String getAnimationReference() {
        return animationReference;
    }
}
