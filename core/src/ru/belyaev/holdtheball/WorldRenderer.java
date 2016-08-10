package ru.belyaev.holdtheball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class WorldRenderer {
    private static final Color CIRCLE_COLOR = Color.BLUE;
    private static final Color VELOCITY_COLOR = Color.WHITE;
    private static final Color ACCELERATION_COLOR = Color.YELLOW;
    private static final float LINE_WIDTH = 5.0f;

    private final World mWorld;
    private final ShapeRenderer mShapeRenderer;

    private boolean mDebug;

    public WorldRenderer(World world, boolean debug) {
        mWorld = world;
        mDebug = debug;
        mShapeRenderer = new ShapeRenderer();
    }

    public WorldRenderer(World world) {
        this(world, false);
    }

    public void render() {
        final float x = mWorld.getBall().getPosition().x;
        final float y = mWorld.getBall().getPosition().y;

        Gdx.gl.glLineWidth(LINE_WIDTH);
        mShapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        mShapeRenderer.setColor(CIRCLE_COLOR);
        mShapeRenderer.circle(x, y, mWorld.getBall().getRadius());
        if (isDebug()) {
            final float vx = mWorld.getBall().getVelocity().x;
            final float vy = mWorld.getBall().getVelocity().y;
            final float ax = mWorld.getBall().getAcceleration().x;
            final float ay = mWorld.getBall().getAcceleration().y;

            mShapeRenderer.setColor(VELOCITY_COLOR);
            mShapeRenderer.line(x, y, x + vx, y + vy);
            mShapeRenderer.setColor(ACCELERATION_COLOR);
            mShapeRenderer.line(x, y, x + ax, y + ay);
        }
        mShapeRenderer.end();
    }

    public void setDebug(boolean debug) {
        mDebug = debug;
    }

    public boolean isDebug() {
        return mDebug;
    }
}
