package ru.belyaev.holdtheball;

import com.badlogic.gdx.math.Vector2;

public class Ball {
    private static final float MAX_VELOCITY = 750.0f;

    private Vector2 mPosition;
    private Vector2 mVelocity = new Vector2(0, 0);
    private Vector2 mAcceleration = new Vector2(0, 0);
    private float mRadius;

    public Ball(Vector2 position, float radius) {
        mPosition = position;
        mRadius = radius;
    }

    public void update(float deltaTime) {
        mPosition.x += mVelocity.x * deltaTime;
        mPosition.y += mVelocity.y * deltaTime;

        mVelocity.x += mAcceleration.x * deltaTime;
        mVelocity.y += mAcceleration.y * deltaTime;
        if(mVelocity.len() > MAX_VELOCITY) {
            mVelocity.nor();
            mVelocity.scl(MAX_VELOCITY);
        }
    }

    public void setPosition(Vector2 position) {
        mPosition = position;
    }

    public Vector2 getPosition() {
        return mPosition;
    }

    public void setPosition(float x, float y) {
        mPosition.x = x;
        mPosition.y = y;
    }

    public float getX() {
        return mPosition.x;
    }

    public void setX(float x) {
        mPosition.x = x;
    }

    public float getY() {
        return mPosition.y;
    }

    public void setY(float y) {
        mPosition.y = y;
    }

    public void setVelocity(Vector2 velocity) {
        mVelocity = velocity;
    }

    public Vector2 getVelocity() {
        return mVelocity;
    }

    public void setAcceleration(Vector2 acceleration) {
        mAcceleration = acceleration;
    }

    public Vector2 getAcceleration() {
        return mAcceleration;
    }

    public void setRadius(float radius) {
        mRadius = radius;
    }

    public float getRadius() {
        return mRadius;
    }
}
