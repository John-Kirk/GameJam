package com.nerds.gamejam.ecs.system;

import com.artemis.Aspect;
import com.artemis.BaseEntitySystem;
import com.artemis.ComponentMapper;
import com.artemis.utils.IntBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.utils.Align;
import com.nerds.gamejam.GameJam;
import com.nerds.gamejam.actor.SpeechActor;
import com.nerds.gamejam.ecs.component.*;
import com.nerds.gamejam.gameplay.character.CrewMember;
import com.nerds.gamejam.util.CachingTextureLoader;
import com.nerds.gamejam.util.InputUtil;
import com.nerds.gamejam.util.TextureReference;

public class SpeechSystem extends BaseEntitySystem {

    private ComponentMapper<SpeechBubbleComponent> speechBubbleComponentComponentMapper;

    private final CachingTextureLoader textureLoader;
    private final InputProcessor inputProcessor;
    private InputProcessor heldInputProcessor;
    private SpeechActor speechActor;

    public SpeechSystem(CachingTextureLoader textureLoader) {
        super(Aspect.all(SpeechBubbleComponent.class));
        this.textureLoader = textureLoader;
        this.inputProcessor = createInputProcessor();
    }

    private InputProcessor createInputProcessor() {
        InputAdapter inputAdapter = new InputAdapter() {
            @Override
            public boolean keyUp(int keycode) {

                if (speechActor != null && keycode == Input.Keys.SPACE) {
                    speechActor.next();
                }

                return super.keyUp(keycode);
            }
        };

        return inputAdapter;
    }

    @Override
    protected void processSystem() {
    }

    @Override
    protected void inserted(int entityId) {
        heldInputProcessor = Gdx.input.getInputProcessor();
        Gdx.input.setInputProcessor(inputProcessor);
        InputUtil.INPUT_ALLOWED = false;

        SpeechBubbleComponent speechBubbleComponent = speechBubbleComponentComponentMapper.get(entityId);
        CrewMember crewMember = speechBubbleComponent.speaker;

        Table wrapper = new Table();
        wrapper.setFillParent(true);

        SpeechActor speechActor = new SpeechActor(GameJam.gameStrings.get(crewMember.getName()), textureLoader.getTexture(new TextureReference(crewMember.getPortraitReference())) ,speechBubbleComponent.lines, () -> {
            world.delete(entityId);
            wrapper.remove();
            Gdx.input.setInputProcessor(heldInputProcessor);
            InputUtil.INPUT_ALLOWED = true;
        });

        this.speechActor = speechActor;
        wrapper.add(speechActor).grow().width(Value.percentWidth(0.8f, wrapper)).height(Value.percentHeight(0.35f, wrapper)).pad(50).align(Align.bottom);
        world.edit(entityId).add(new ActorComponent(wrapper));
    }

    public interface EndOfSpeechCallback {
        void execute();
    }
}
