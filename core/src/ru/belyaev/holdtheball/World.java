package ru.belyaev.holdtheball;

import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class World {

    private static final float MIN_VELOCITY = 50.0f;
    private static final float MAX_VELOCITY = 100.0f;
    private static final float MIN_ACCELERATION = 50.0f;
    private static final float MAX_ACCELERATION = 1000.0f;
    private static final float MIN_TIME_TO_TICK = 0.5f;
    private static final float MAX_TIME_TO_TICK = 1.0f;
    private static final float TIME_TO_HARDCORE = 60.0f; // время через которое все параметры должны достигнуть своих пиков

    private static final float BALL_RADIUS = 50.0f;

    private final int mWidth;
    private final int mHeight;
    private final Ball mBall;
    private final Random mRandom;

    private float mTimeToTick = 0;
    private float mTime = 0; // == score // прошедшее время

    public World(int width, int height) {
        mWidth = width;
        mHeight = height;
        // mBall = new Ball(new Vector2(width / 2, height / 2), BALL_RADIUS);
        mBall = new Ball(new Vector2(width / 2, height / 2), BALL_RADIUS);
        mRandom = new Random(System.currentTimeMillis());

//        mBall.setAcceleration(new Vector2(-30, +30));
//        mBall.setAcceleration(new Vector2(10,-10));
    }

    public void update(float deltaTime) {
        mBall.update(deltaTime);
        checkCollisionBorders();
        //
        mTimeToTick -= deltaTime;
        if (mTimeToTick <= 0) {
            System.out.println("tick");
            System.out.println("v: " + mBall.getVelocity().len());
            mTimeToTick = mRandom.nextFloat() * MAX_TIME_TO_TICK;
            mBall.setAcceleration(getRandomAcceleration());
        }
        mTime += deltaTime;
    }

    private void checkCollisionBorders() {
        if (mBall.getX() - mBall.getRadius() < 0) {
            mBall.setX(0 + mBall.getRadius());
            final float angleX = mBall.getVelocity().angle(Vector2.Y);
            mBall.getVelocity().setAngle(90 + angleX);
        }
        if (mBall.getX() + mBall.getRadius() > mWidth) {
            mBall.setX(mWidth - mBall.getRadius());
            final float angleX = mBall.getVelocity().angle(Vector2.Y);
            mBall.getVelocity().setAngle(90 + angleX);
        }
        if (mBall.getY() - mBall.getRadius() < 0) {
            mBall.setY(0 + mBall.getRadius());
            final float angleY = mBall.getVelocity().angle(Vector2.X);
            mBall.getVelocity().setAngle(angleY);
        }
        if (mBall.getY() + mBall.getRadius() > mHeight) {
            mBall.setY(mHeight - mBall.getRadius());
            final float angleY = mBall.getVelocity().angle(Vector2.X);
            mBall.getVelocity().setAngle(angleY);
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
}
