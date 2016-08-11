package ru.belyaev.holdtheball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.text.DecimalFormat;

public class WorldRenderer {
    private static final Color CIRCLE_COLOR = Color.BLACK;
    private static final Color VELOCITY_COLOR = Color.BLUE;
    private static final Color ACCELERATION_COLOR = Color.YELLOW;
    private static final float LINE_WIDTH = 5.0f;

    private final World mWorld;
    private final ShapeRenderer mShapeRenderer;
    private final SpriteBatch mSpriteBatch;
    private final BitmapFont mBitmapFont;
    private final GlyphLayout mGlyphLayout;
    private final DecimalFormat mDecimalFormat = new DecimalFormat("Score: 0.000");

    private boolean mDebug;

    public WorldRenderer(World world, boolean debug) {
        mWorld = world;
        mDebug = debug;
        mShapeRenderer = new ShapeRenderer();
        mSpriteBatch = new SpriteBatch();

        mBitmapFont = new BitmapFont();
        mBitmapFont.setColor(Color.BLACK);
        mGlyphLayout = new GlyphLayout();
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
            mShapeRenderer.line(x, y, x + vx / 10, y + vy / 10);
            mShapeRenderer.setColor(ACCELERATION_COLOR);
            mShapeRenderer.line(x, y, x + ax / 10, y + ay / 10);
        }
        mShapeRenderer.end();

        mSpriteBatch.begin();
        mGlyphLayout.setText(mBitmapFont, mDecimalFormat.format(mWorld.getTime()));
        mBitmapFont.draw(mSpriteBatch, mGlyphLayout, 0, mWorld.getHeight() - mGlyphLayout.height);
        mSpriteBatch.end();
    }

    public void setDebug(boolean debug) {
        mDebug = debug;
    }

    public boolean isDebug() {
        return mDebug;
    }
}
