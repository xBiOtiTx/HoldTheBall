package ru.belyaev.holdtheball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class WorldRenderer {

    private static final Color CLEAR_COLOR = Color.WHITE;
    private static final Color CIRCLE_COLOR = Color.BLUE;
    private static final float LINE_WIDTH = 5.0f;

    private final World mWorld;
    private final ShapeRenderer mShapeRenderer;

    public WorldRenderer(World world) {
        mWorld = world;
        mShapeRenderer = new ShapeRenderer();
    }

    public void render() {
        Gdx.gl.glLineWidth(LINE_WIDTH);
        mShapeRenderer.setColor(CIRCLE_COLOR);
        mShapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        mShapeRenderer.circle(mWorld.getBall().getPosition().x, mWorld.getBall().getPosition().y, mWorld.getBall().getRadius()); // LINE_WIDTH
        mShapeRenderer.end();
    }
}
