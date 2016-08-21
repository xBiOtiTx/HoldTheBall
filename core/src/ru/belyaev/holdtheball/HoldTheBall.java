package ru.belyaev.holdtheball;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import ru.belyaev.holdtheball.screen.GameScreen;
import ru.belyaev.holdtheball.util.Assets;

public class HoldTheBall extends Game {
    private ShapeRenderer mShapeRenderer;
    private SpriteBatch mSpriteBatch;

    @Override
    public void create() {
        Assets.load();
        mShapeRenderer = new ShapeRenderer();
        mSpriteBatch = new SpriteBatch();
        setScreen(new GameScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        Assets.dispose();
        mShapeRenderer.dispose();
        mSpriteBatch.dispose();
    }

    public ShapeRenderer getShapeRenderer() {
        return mShapeRenderer;
    }

    public SpriteBatch getSpriteBatch() {
        return mSpriteBatch;
    }
}
