package ru.belyaev.holdtheball;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;

import java.util.Random;

public class World {

    private static final float MIN_VELOCITY = 50.0f;
    private static final float MAX_VELOCITY = 100.0f;
    private static final float MIN_ACCELERATION = 50.0f;
    private static final float MAX_ACCELERATION = 5000.0f;
    private static final float MIN_TIME_TO_TICK = 0.5f;
    private static final float MAX_TIME_TO_TICK = 0.75f;

    private static final float BALL_MIN_RADIUS = 25.0f;
    private static final float BALL_MAX_RADIUS = 75.0f;
    private static final float BALL_RADIUS = 75.0f;

    private final int mWidth;
    private final int mHeight;
    private final Ball mBall;
    private final Random mRandom;

    private float mTimeToTick = 0;
    private float mTime = 0; // == score // прошедшее время

    // TODO WorldState
    public enum State {
        RUNNING,
        GAME_OVER
    }

    public World(int width, int height) {
        mWidth = width;
        mHeight = height;
        mBall = new Ball(new Vector2(width / 2, height / 2), BALL_RADIUS);
        mRandom = new Random(System.currentTimeMillis());
    }

    public void update(float deltaTime) {
        mBall.update(deltaTime);
        checkCollisionBorders();
        updateAcceleration(deltaTime);
        mTime += deltaTime;
    }

    private void updateAcceleration(float deltaTime) {
        mTimeToTick -= deltaTime;
        if (mTimeToTick <= 0) {
            mTimeToTick = mRandom.nextFloat() * MAX_TIME_TO_TICK;
            mBall.setAcceleration(getRandomAcceleration());
        }
    }

    private void checkCollisionBorders() {
        if (mBall.getX() - mBall.getRadius() < 0) {
            mBall.setX(0 + mBall.getRadius());
            final float angleX = mBall.getVelocity().angle(Vector2.Y);
            mBall.getVelocity().setAngle(90 + angleX);

            final float aangleX = mBall.getAcceleration().angle(Vector2.Y);
            mBall.getAcceleration().setAngle(90 + aangleX);
        }
        if (mBall.getX() + mBall.getRadius() > mWidth) {
            mBall.setX(mWidth - mBall.getRadius());
            final float angleX = mBall.getVelocity().angle(Vector2.Y);
            mBall.getVelocity().setAngle(90 + angleX);

            final float aangleX = mBall.getAcceleration().angle(Vector2.Y);
            mBall.getAcceleration().setAngle(90 + aangleX);
        }
        if (mBall.getY() - mBall.getRadius() < 0) {
            mBall.setY(0 + mBall.getRadius());
            final float angleY = mBall.getVelocity().angle(Vector2.X);
            mBall.getVelocity().setAngle(angleY);

            final float aangleY = mBall.getAcceleration().angle(Vector2.X);
            mBall.getAcceleration().setAngle(aangleY);
        }
        if (mBall.getY() + mBall.getRadius() > mHeight) {
            mBall.setY(mHeight - mBall.getRadius());
            final float angleY = mBall.getVelocity().angle(Vector2.X);
            mBall.getVelocity().setAngle(angleY);

            final float aangleY = mBall.getAcceleration().angle(Vector2.X);
            mBall.getAcceleration().setAngle(aangleY);
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
        return distance(mBall.getX(), mBall.getY(), x, y) <= mBall.getRadius();
    }

    // *********************************************************************************
    // getters
    // *********************************************************************************
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
}
