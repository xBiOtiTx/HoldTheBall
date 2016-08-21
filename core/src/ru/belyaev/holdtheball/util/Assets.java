package ru.belyaev.holdtheball.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

// TODO singleton and implement disposable
public class Assets {
    public static Texture sBallTexture;
    public static Sound sBallHitSound;

    public static BitmapFont sRobotoRegularBitmapFont;
    public static BitmapFont sRobotoMediumBitmapFont;

    public static Skin sSkin;

    private static void loadBallTexture() {
        final int r = Dimens.BALL_RADIUS_PX;
        final int w = Dimens.BALL_BORDER_WIDTH_PX;

        Pixmap.setBlending(Pixmap.Blending.None);
        final Pixmap pixmap = new Pixmap(2 * r + 1, 2 * r + 1, Pixmap.Format.RGBA8888);

        pixmap.setColor(Color.BLACK);
        pixmap.fillCircle(r, r, r);

        pixmap.setColor(Color.CLEAR);
        pixmap.fillCircle(r, r, r - w);

        sBallTexture = new Texture(pixmap);
        pixmap.dispose();
    }

    private static void loadSounds() {
        sBallHitSound = Gdx.audio.newSound(Gdx.files.internal("sounds/ball-hit.wav"));
    }

    private static void loadFonts() {
        FreeTypeFontGenerator generator;
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = Dimens.TEXT_SIZE_DEFAULT_PX;

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Roboto-Regular.ttf"));
        sRobotoRegularBitmapFont = generator.generateFont(parameter);
        generator.dispose();

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Roboto-Medium.ttf"));
        sRobotoMediumBitmapFont = generator.generateFont(parameter);
        generator.dispose();
    }

    private static void loadSkins() {
        sSkin = new Skin();
        sSkin.add("roboto-regular", sRobotoRegularBitmapFont);
        sSkin.add("roboto-medium", sRobotoMediumBitmapFont);

        sSkin.add("black-label-style", new Label.LabelStyle(sSkin.getFont("roboto-regular"), Color.BLACK), Label.LabelStyle.class);
        sSkin.add("white-label-style", new Label.LabelStyle(sSkin.getFont("roboto-medium"), Color.WHITE), Label.LabelStyle.class);

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE); // TODO зачемто, да нужно. иначе унопка тёмная
        pixmap.fill();
        sSkin.add("button-background", new Texture(pixmap));
        pixmap.dispose();

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = sSkin.newDrawable("button-background", Color.GRAY);
        textButtonStyle.down = sSkin.newDrawable("button-background", Color.DARK_GRAY);
        textButtonStyle.checked = sSkin.newDrawable("button-background", Color.DARK_GRAY);
        textButtonStyle.over = sSkin.newDrawable("button-background", Color.LIGHT_GRAY);
        textButtonStyle.font = sSkin.getFont("roboto-medium");
        textButtonStyle.fontColor = Color.WHITE;
        sSkin.add("button-style", textButtonStyle, TextButton.TextButtonStyle.class);
    }

    public static void load() {
        loadBallTexture();
        loadSounds();
        loadFonts();
        loadSkins();
    }

    public static void dispose() {
        sBallTexture.dispose();
        sBallHitSound.dispose();

        // TODO dispose in Skin?
        //sRobotoRegularBitmapFont.dispose();
        //sRobotoMediumBitmapFont.dispose();

        sSkin.dispose();
    }
}
