package ru.belyaev.holdtheball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import ru.belyaev.holdtheball.ui.Styles;

public class WorldRenderer {
    private static final Color CIRCLE_COLOR = Color.BLACK;
    private static final Color VELOCITY_COLOR = Color.BLUE;
    private static final Color ACCELERATION_COLOR = Color.YELLOW;
    private static final float LINE_WIDTH = 8.0f;

    private final World mWorld;
    private final ShapeRenderer mShapeRenderer;
    private final SpriteBatch mSpriteBatch;
    private final BitmapFont mBitmapFont;
    private final GlyphLayout mGlyphLayout;

    private boolean mDebug;

    Pixmap mPixmap;
    Texture mPixmapTexture;

    public WorldRenderer(World world, boolean debug) {
        mWorld = world;
        mDebug = debug;
        mShapeRenderer = new ShapeRenderer();
        mSpriteBatch = new SpriteBatch();

        //mBitmapFont = new BitmapFont();
        mBitmapFont = Styles.getFont(Styles.TEXT_SIZE_NORMAL);
        mBitmapFont.setColor(Color.BLACK);
        mGlyphLayout = new GlyphLayout();

        Pixmap.setBlending(Pixmap.Blending.None);
        mPixmap = new Pixmap(257, 257, Pixmap.Format.RGBA8888);

        mPixmap.setColor(Color.BLACK);
        mPixmap.fillCircle(128, 128, 128);

        mPixmap.setColor(Color.LIGHT_GRAY);
        mPixmap.fillCircle(128, 128, 120);

        mPixmapTexture = new Texture(mPixmap);
        mPixmap.dispose();
    }

    public WorldRenderer(World world) {
        this(world, false);
    }

    public void render() {
        final float x = mWorld.getBall().getPosition().x;
        final float y = mWorld.getBall().getPosition().y;

        mSpriteBatch.begin();
        mGlyphLayout.setText(mBitmapFont, String.format(Locale.US, "Time: %.3f", mWorld.getTime()));
        final int paddingLeft = Styles.toDpi(8);
        final int paddingTop = Styles.toDpi(4);
        mSpriteBatch.draw(mPixmapTexture, x - mWorld.getBall().getRadius(), y - mWorld.getBall().getRadius());
        mBitmapFont.draw(mSpriteBatch, mGlyphLayout, paddingLeft, mWorld.getHeight() - mGlyphLayout.height/2 - paddingTop);
        mSpriteBatch.end();
    }

    public void setDebug(boolean debug) {
        mDebug = debug;
    }

    public boolean isDebug() {
        return mDebug;
    }
}
