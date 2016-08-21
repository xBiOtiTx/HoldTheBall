package ru.belyaev.holdtheball;

import com.badlogic.gdx.math.Vector2;

import ru.belyaev.holdtheball.util.Dimens;

public class World {
    private final int mWidth;
    private final int mHeight;
    private final Ball mBall;
    private final Accelerator mAccelerator = new Accelerator();
    private final WorldListener mWorldListener;
    private final Vector2 mTouchPosition = new Vector2();

    private WorldState mWorldState = WorldState.READY;
    private float mTime = 0;

    public World(int width, int height, WorldListener worldListener) {
        mWidth = width;
        mHeight = height;
        mWorldListener = worldListener;
        mBall = new Ball(new Vector2(width / 2, height / 2), Dimens.BALL_RADIUS_PX);
    }

    // =============================================================================================

    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }

    public Ball getBall() {
        return mBall;
    }

    public Accelerator getAccelerator() {
        return mAccelerator;
    }

    public WorldListener getWorldListener() {
        return mWorldListener;
    }

    public Vector2 getTouchPosition() {
        return mTouchPosition;
    }

    public WorldState getWorldState() {
        return mWorldState;
    }

    public void setWorldState(WorldState worldState) {
        mWorldState = worldState;
    }

    public float getTime() {
        return mTime;
    }

    public void setTime(float time) {
        mTime = time;
    }

    public void addTime(float deltaTime) {
        mTime += deltaTime;
    }

    // =============================================================================================

    public enum WorldState {
        READY,
        RUNNING,
        GAME_OVER
    }

    public interface WorldListener {
        void onBound();
    }
}
