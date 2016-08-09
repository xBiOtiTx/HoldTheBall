package ru.belyaev.holdtheball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class WorldRenderer {

    private static final Color CLEAR_COLOR = Color.WHITE;
    private static final Color CIRCLE_COLOR = Color.BLUE;
    private static final Color VELOCITY_COLOR = Color.WHITE;
    private static final Color ACCELERATION_COLOR = Color.YELLOW;
    private static final float LINE_WIDTH = 5.0f;

    private final World mWorld;
    private final ShapeRenderer mShapeRenderer;

    public WorldRenderer(World world) {
        mWorld = world;
        mShapeRenderer = new ShapeRenderer();
    }

    public void render() {
        Gdx.gl.glLineWidth(LINE_WIDTH);

        final float x = mWorld.getBall().getPosition().x;
        final float y = mWorld.getBall().getPosition().y;
        final float vx = mWorld.getBall().getVelocity().x;
        final float vy = mWorld.getBall().getVelocity().y;
        final float ax = mWorld.getBall().getAcceleration().x;
        final float ay = mWorld.getBall().getAcceleration().y;

        mShapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        mShapeRenderer.setColor(CIRCLE_COLOR);
        mShapeRenderer.circle(x, y, mWorld.getBall().getRadius());
        mShapeRenderer.setColor(VELOCITY_COLOR);
        mShapeRenderer.line(x, y, x + vx, y + vy);
        mShapeRenderer.setColor(ACCELERATION_COLOR);
        mShapeRenderer.line(x, y, x + ax, y + ay);
        mShapeRenderer.end();
    }
}
