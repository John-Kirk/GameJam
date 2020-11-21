package com.nerds.gamejam.ecs.component;

import com.artemis.Component;
import com.badlogic.gdx.utils.Array;
import com.nerds.gamejam.gameplay.character.CrewMember;


public class SpeechBubbleComponent extends Component {

    public CrewMember speaker;
    public Array<String> lines;

    public SpeechBubbleComponent(CrewMember speaker, Array<String> lines) {
        this.speaker = speaker;
        this.lines = lines;
    }

    public SpeechBubbleComponent() {
    }
}
