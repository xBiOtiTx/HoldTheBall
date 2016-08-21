package ru.belyaev.holdtheball;

import com.badlogic.gdx.math.Vector2;

public class WorldController {
    private static final float MAX_VELOCITY = 900.0f;

    private final World mWorld;

    public WorldController(World world) {
        mWorld = world;
    }

    public void update(float deltaTime) {
        updateBall(deltaTime);
        updateAcceleration(deltaTime);
        checkCollisionBorders();
        checkGameOver();
        mWorld.addTime(deltaTime);
    }

    private void updateBall(float deltaTime) {
        final Ball ball = mWorld.getBall();
        final Vector2 position = ball.getPosition();
        final Vector2 velocity = ball.getVelocity();
        final Vector2 acceleration = ball.getAcceleration();

        position.x += velocity.x * deltaTime;
        position.y += velocity.y * deltaTime;

        velocity.x += acceleration.x * deltaTime;
        velocity.y += acceleration.y * deltaTime;
        if (velocity.len() > MAX_VELOCITY) {
            velocity.nor();
            velocity.scl(MAX_VELOCITY);
        }
    }

    private void updateAcceleration(float deltaTime) {
        mWorld.getAccelerator().apply(mWorld.getBall(),deltaTime);
    }

    private void checkCollisionBorders() {
        final float width = mWorld.getWidth();
        final float height = mWorld.getHeight();
        final Ball ball = mWorld.getBall();

        if (ball.getX() - ball.getScaledRadius() < 0) {
            ball.setX(0 + ball.getScaledRadius());
            final float angleX = ball.getVelocity().angle(Vector2.Y);
            ball.getVelocity().setAngle(90 + angleX);

            final float aangleX = ball.getAcceleration().angle(Vector2.Y);
            ball.getAcceleration().setAngle(90 + aangleX);

            mWorld.getWorldListener().onBound();
        }
        if (ball.getX() + ball.getScaledRadius() > width) {
            ball.setX(width - ball.getScaledRadius());
            final float angleX = ball.getVelocity().angle(Vector2.Y);
            ball.getVelocity().setAngle(90 + angleX);

            final float aangleX = ball.getAcceleration().angle(Vector2.Y);
            ball.getAcceleration().setAngle(90 + aangleX);

            mWorld.getWorldListener().onBound();
        }
        if (ball.getY() - ball.getScaledRadius() < 0) {
            ball.setY(0 + ball.getScaledRadius());
            final float angleY = ball.getVelocity().angle(Vector2.X);
            ball.getVelocity().setAngle(angleY);

            final float aangleY = ball.getAcceleration().angle(Vector2.X);
            ball.getAcceleration().setAngle(aangleY);

            mWorld.getWorldListener().onBound();
        }
        if (ball.getY() + ball.getScaledRadius() > height) {
            ball.setY(height - ball.getScaledRadius());
            final float angleY = ball.getVelocity().angle(Vector2.X);
            ball.getVelocity().setAngle(angleY);

            final float aangleY = ball.getAcceleration().angle(Vector2.X);
            ball.getAcceleration().setAngle(aangleY);

            mWorld.getWorldListener().onBound();
        }
    }

    private void checkGameOver() {
        if (!mWorld.getBall().hit(mWorld.getTouchPosition())) {
            mWorld.setWorldState(World.WorldState.GAME_OVER);
        }
    }

    // =============================================================================================
    // touch
    // =============================================================================================

    public void touchDown(int x, int y) {
        if (mWorld.getWorldState() == World.WorldState.READY && mWorld.getBall().hit(x, y)) {
            mWorld.setWorldState(World.WorldState.RUNNING);
            mWorld.getTouchPosition().set(x, y);
        }
    }

    public void touchUp(int x, int y) {
        if (mWorld.getWorldState() == World.WorldState.RUNNING) {
            mWorld.setWorldState(World.WorldState.GAME_OVER);
        }
    }

    public void touchDragged(int x, int y) {
        mWorld.getTouchPosition().set(x, y);
    }
}
