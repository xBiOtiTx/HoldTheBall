package ru.belyaev.holdtheball;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import ru.belyaev.holdtheball.util.Assets;

public class WorldRenderer {
    private final World mWorld;
    private final ShapeRenderer mShapeRenderer;
    private final SpriteBatch mSpriteBatch;

    public WorldRenderer(World world, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch) {
        mWorld = world;
        mShapeRenderer = shapeRenderer;
        mSpriteBatch = spriteBatch;
    }

    public WorldRenderer(World world) {
        this(world, new ShapeRenderer(), new SpriteBatch());
    }

    public void render() {
        final float r = mWorld.getBall().getScaledRadius();
        final float x = mWorld.getBall().getPosition().x;
        final float y = mWorld.getBall().getPosition().y;

//        mShapeRenderer.setColor(Color.LIGHT_GRAY);
//        mShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        mShapeRenderer.circle(mWorld.getWidth() / 2, mWorld.getHeight() / 2, Math.min(mWorld.getWidth() / 2, mWorld.getHeight() / 2));
//        mShapeRenderer.end();

        mSpriteBatch.begin();
        mSpriteBatch.draw(Assets.sBallTexture, x - r, y - r, 2 * r, 2 * r);
        mSpriteBatch.end();
    }
}
