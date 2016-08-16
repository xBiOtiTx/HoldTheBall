package ru.belyaev.holdtheball;

import com.badlogic.gdx.math.Vector2;


public class BallAccelerator {
    private static final float MAX_VELOCITY = 900.0f;
    private static final float MAX_ACCELERATION = 6000.0f;
    private static final float MAX_TIME_TO_ACCELERATE = 0.75f;

    private final Ball mBall;
    private final float mStartTime;
    private float mEffectTime = 0;

    public BallAccelerator(Ball ball, float startTime) {
        mBall = ball;
        mStartTime = startTime;
    }

    public void update(float deltaTime) {
        final Vector2 position = mBall.getPosition();
        final Vector2 velocity = mBall.getVelocity();
        final Vector2 acceleration = mBall.getAcceleration();

        position.x += velocity.x * deltaTime;
        position.y += velocity.y * deltaTime;

        velocity.x += acceleration.x * deltaTime;
        velocity.y += acceleration.y * deltaTime;
        if (velocity.len() > MAX_VELOCITY) {
            velocity.nor();
            velocity.scl(MAX_VELOCITY);
        }

        mEffectTime += deltaTime;
    }
}
