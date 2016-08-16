package ru.belyaev.holdtheball;

import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import ru.belyaev.holdtheball.ui.Styles;

public class World {

    private static final float MAX_ACCELERATION = 6000.0f;
    private static final float MAX_TIME_TO_TICK = 0.75f;

    private static final float BALL_MIN_RADIUS = 25.0f;
    private static final float BALL_MAX_RADIUS = 75.0f;
    private static final float BALL_RADIUS = 56.0f;

    private static final float HIT_ERROR_DP = 48;

    private final int mWidth;
    private final int mHeight;
    private final Ball mBall;
    private final Random mRandom;
    private final WorldListener mWorldListener;

    private float mTimeToTick = 0;
    private float mTime = 0;

    public enum WorldState {
        RUNNING,
        GAME_OVER
    }

    private WorldState mWorldState = WorldState.RUNNING;

    public World(int width, int height, WorldListener worldListener) {
        mWidth = width;
        mHeight = height;
        mWorldListener = worldListener;
        mBall = new Ball(new Vector2(width / 2, height / 2), Styles.dp(BALL_RADIUS));
        mRandom = new Random(System.currentTimeMillis());
    }

    public void update(float deltaTime) {
        switch (mWorldState) {
            case RUNNING:
                updateRunning(deltaTime);
                break;

            case GAME_OVER:
                updateGameOver(deltaTime);
                break;
        }
    }

    private void updateRunning(float deltaTime) {
        // mScaleEffect.apply(mBall,deltaTime);
        mBall.update(deltaTime);
        checkCollisionBorders();
        updateAcceleration(deltaTime);
        mTime += deltaTime;
    }

    private void updateGameOver(float deltaTime) {

    }

    private void updateAcceleration(float deltaTime) {
        mTimeToTick -= deltaTime;
        if (mTimeToTick <= 0) {
            mTimeToTick = mRandom.nextFloat() * MAX_TIME_TO_TICK;
            mBall.setAcceleration(getRandomAcceleration());
        }
    }

    private void checkCollisionBorders() {
        if (mBall.getX() - mBall.getScaledRadius() < 0) {
            mBall.setX(0 + mBall.getScaledRadius());
            final float angleX = mBall.getVelocity().angle(Vector2.Y);
            mBall.getVelocity().setAngle(90 + angleX);

            final float aangleX = mBall.getAcceleration().angle(Vector2.Y);
            mBall.getAcceleration().setAngle(90 + aangleX);
            mWorldListener.onBound();
        }
        if (mBall.getX() + mBall.getScaledRadius() > mWidth) {
            mBall.setX(mWidth - mBall.getScaledRadius());
            final float angleX = mBall.getVelocity().angle(Vector2.Y);
            mBall.getVelocity().setAngle(90 + angleX);

            final float aangleX = mBall.getAcceleration().angle(Vector2.Y);
            mBall.getAcceleration().setAngle(90 + aangleX);
            mWorldListener.onBound();
        }
        if (mBall.getY() - mBall.getScaledRadius() < 0) {
            mBall.setY(0 + mBall.getScaledRadius());
            final float angleY = mBall.getVelocity().angle(Vector2.X);
            mBall.getVelocity().setAngle(angleY);

            final float aangleY = mBall.getAcceleration().angle(Vector2.X);
            mBall.getAcceleration().setAngle(aangleY);
            mWorldListener.onBound();
        }
        if (mBall.getY() + mBall.getScaledRadius() > mHeight) {
            mBall.setY(mHeight - mBall.getScaledRadius());
            final float angleY = mBall.getVelocity().angle(Vector2.X);
            mBall.getVelocity().setAngle(angleY);

            final float aangleY = mBall.getAcceleration().angle(Vector2.X);
            mBall.getAcceleration().setAngle(aangleY);
            mWorldListener.onBound();
        }
    }

    public Vector2 getRandomAcceleration() {
        final float rx = mRandom.nextFloat() - 0.5f;
        final float ry = mRandom.nextFloat() - 0.5f;
        final Vector2 acceleration = new Vector2(rx, ry); // allocate! 
        acceleration.nor();
        acceleration.scl(mRandom.nextFloat() * MAX_ACCELERATION);
        return acceleration;
    }

    public float distance(float x1, float y1, float x2, float y2) {
        final float dx = x2 - x1;
        final float dy = y2 - y1;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    public boolean hit(float x, float y) {
        return distance(mBall.getX(), mBall.getY(), x, y) <= (mBall.getScaledRadius() + Styles.dp(HIT_ERROR_DP));
    }

    // =============================================================================================
    // getters
    // =============================================================================================

    public float getWidth() {
        return mWidth;
    }

    public float getHeight() {
        return mHeight;
    }

    public Ball getBall() {
        return mBall;
    }


    public float getTime() {
        return mTime;
    }

    // =============================================================================================
    // World Listener
    // =============================================================================================

    public interface WorldListener {
        void onBound();

        void onGameOver();
    }
}
