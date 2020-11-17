package com.nerds.gamejam.ecs.system;

import com.artemis.Aspect;
import com.artemis.BaseEntitySystem;
import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.nerds.gamejam.GameJam;
import com.nerds.gamejam.ecs.component.CircleComponent;
import com.nerds.gamejam.ecs.component.*;
import com.nerds.gamejam.util.CachingTextureLoader;
import com.nerds.gamejam.util.TextureReference;

import java.util.HashMap;
import java.util.Map;

@Wire
public class RenderSystem extends BaseEntitySystem {

    private ComponentMapper<PositionComponent> positionMapper;
    private ComponentMapper<TextureReferenceComponent> textureReferenceMapper;
    private ComponentMapper<ScaleComponent> scaleMapper;
    private ComponentMapper<BodyComponent> bodyMapper;
    private ComponentMapper<FontComponent> fontMapper;
    private ComponentMapper<CircleComponent> circleMapper;
    private ComponentMapper<LayerComponent> layerMapper;
    private final CachingTextureLoader textureLoader;
    private CameraSystem cameraSystem;
    private ExtendViewport scalingViewport;
    private Batch batch;
    private BitmapFont font;
    private final Map<Integer, Array<Integer>> layerMap;
    private ShapeRenderer circleRenderer;

    public RenderSystem(CachingTextureLoader textureLoader) {
        super(Aspect.one(TextureReferenceComponent.class, AnimationComponent.class, CircleComponent.class));
        this.textureLoader = textureLoader;
        this.layerMap = new HashMap<>();
    }

    @Override
    protected void initialize() {
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
        this.circleRenderer = new ShapeRenderer();

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        float worldWidth = GameJam.PLANET_VIEW_HEIGHT * w / h;
        int worldHeight = GameJam.PLANET_VIEW_HEIGHT;
        scalingViewport = new ExtendViewport(worldWidth, worldHeight, cameraSystem.camera);
        scalingViewport.update((int)w, (int)h);
        scalingViewport.apply();
        cameraSystem.camera.position.add(worldWidth * -2f, worldHeight / -2f, 0);
    }

    @Override
    protected void begin() {
        this.batch.setProjectionMatrix(cameraSystem.camera.combined);
        this.circleRenderer.setProjectionMatrix(cameraSystem.camera.combined);
    }

    @Override
    protected void inserted(int entityId) {
        LayerComponent layerComponent = layerMapper.get(entityId);
        System.out.println("Adding entity " + entityId);

        if (layerMap.get(layerComponent.layer) != null) {
            Array<Integer> entityIds = layerMap.get(layerComponent.layer);
            entityIds.add(entityId);
        } else {
            Array<Integer> entityIds = new Array<>();
            entityIds.add(entityId);
            layerMap.put(layerComponent.layer, entityIds);
        }
    }

    @Override
    protected void removed(int entityId) {
        LayerComponent layerComponent = layerMapper.get(entityId);
        if (layerMap.containsKey(layerComponent.layer)) {
            Array<Integer> entityIds = layerMap.get(entityId);
            entityIds.removeValue(entityId, true);
        }
    }

    @Override
    protected void processSystem() {
        scalingViewport.apply();

        layerMap.keySet().stream().sorted(Integer::compareTo).forEach(i -> {
            if (circleMapper.get(layerMap.get(i).get(0)) != null) {
                layerMap.get(i).forEach(e -> {
                    this.circleRenderer.begin(ShapeRenderer.ShapeType.Line);
                    drawCircle(e);
                    this.circleRenderer.end();
                });

            } else {
                layerMap.get(i).forEach(e -> {
                    PositionComponent position = positionMapper.get(e);
                    TextureReferenceComponent textureReference = textureReferenceMapper.get(e);
                    BodyComponent bodyComponent = bodyMapper.get(e);
                    ScaleComponent scaleComponent = scaleMapper.get(e);

                    if (position != null && bodyComponent != null) {
                        float width = scaleComponent != null ? bodyComponent.width * scaleComponent.x : bodyComponent.width;
                        float height = scaleComponent != null ? bodyComponent.height * scaleComponent.y : bodyComponent.height;

                        batch.begin();
                        for (TextureReference reference : textureReference.references) {
                            TextureRegion toDraw = textureLoader.getTexture(reference);
                            batch.setColor(reference.getColor());
                            batch.draw(toDraw, position.x, position.y, width, height);
                        }

                        drawFont(e);
                        batch.end();
                    }
                });
            }
        });
    }

    private void drawFont(int e) {
        FontComponent fontComponent = this.fontMapper.get(e);
        if (fontComponent != null) {
            this.font.setColor(Color.WHITE);
            this.font.draw(this.batch, fontComponent.text, fontComponent.x, fontComponent.y);
        }
    }

    private void drawCircle(int e) {
        CircleComponent circleComponent = this.circleMapper.get(e);
        if (circleComponent != null) {
            this.circleRenderer.setColor(Color.LIGHT_GRAY);
            this.circleRenderer.circle(circleComponent.x , circleComponent.y, circleComponent.radius);
        }
    }

    public void resize(int width, int height) {
        scalingViewport.update(width, height);
        scalingViewport.apply();
        cameraSystem.resize(width, height);
    }

    @Override
    protected void dispose() {
        this.batch.dispose();
        this.circleRenderer.dispose();
        this.font.dispose();
    }
}
