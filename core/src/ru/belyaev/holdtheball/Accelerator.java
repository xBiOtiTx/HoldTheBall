package ru.belyaev.holdtheball;

import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Accelerator {
    private static final float MAX_ACCELERATION = 6000.0f;
    private static final float MAX_TIME_TO_ACCELERATE = 0.75f;

    private float mTimeToAccelerate = 0;
    private final Random mRandom = new Random();

    public void apply(Ball ball, float deltaTime) {
        mTimeToAccelerate -= deltaTime;
        if (mTimeToAccelerate <= 0) {
            mTimeToAccelerate = mRandom.nextFloat() * MAX_TIME_TO_ACCELERATE;
            ball.getAcceleration().set(getRandomAcceleration());
        }
    }

    private final Vector2 tmp = new Vector2();

    private Vector2 getRandomAcceleration() {
        final float rx = mRandom.nextFloat() - 0.5f;
        final float ry = mRandom.nextFloat() - 0.5f;
        tmp.set(rx, ry);
        tmp.nor();
        tmp.scl(mRandom.nextFloat() * MAX_ACCELERATION);
        return tmp;
    }
}
