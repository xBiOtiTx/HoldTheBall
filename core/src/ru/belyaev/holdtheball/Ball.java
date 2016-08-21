package ru.belyaev.holdtheball;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

import ru.belyaev.holdtheball.util.Dimens;

public class Ball {
    private static final float MAX_VELOCITY = 800.0f; // TODO -> config

    private Vector2 mPosition;
    private Vector2 mVelocity = new Vector2(0, 0);
    private Vector2 mAcceleration = new Vector2(0, 0);
    private int mRadius;
    private float mScale = 1.0f;

    public Ball(Vector2 position, int radius) {
        mPosition = position;
        mRadius = radius;
    }

    public void update(float deltaTime) {
        mPosition.x += mVelocity.x * deltaTime;
        mPosition.y += mVelocity.y * deltaTime;

        mVelocity.x += mAcceleration.x * deltaTime;
        mVelocity.y += mAcceleration.y * deltaTime;
        if (mVelocity.len() > MAX_VELOCITY) {
            mVelocity.nor();
            mVelocity.scl(MAX_VELOCITY);
        }
    }

    public boolean hit(float x, float y) {
        return mPosition.dst(x,y) <= (getScaledRadius() + Dimens.HIT_ERROR_PX);
    }

    public boolean hit(Vector2 position) {
        return mPosition.dst(position) <= (getScaledRadius() + Dimens.HIT_ERROR_PX);
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

    public void setRadius(int radius) {
        mRadius = radius;
    }

    public int getRadius() {
        return mRadius;
    }

    public float getScale() {
        return mScale;
    }

    public void setScale(float scale) {
        mScale = scale;
    }

    public float getScaledRadius() {
        return mRadius * mScale;
    }
}
