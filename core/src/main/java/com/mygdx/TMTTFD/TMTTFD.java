package com.mygdx.TMTTFD;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class TMTTFD extends Game {

    public SpriteBatch batch, textBatch;
    public ShapeRenderer shapeRenderer;
    BitmapFont smallFont, mediumFont, bigFont;
    public OrthographicCamera camera, textCamera;
    public AssetManager manager;

    @Override
    public void create() {

        manager = new AssetManager();
        batch = new SpriteBatch();
        textBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);

        FreeTypeFontGenerator generator = new
            FreeTypeFontGenerator(Gdx.files.internal("ARCADECLASSIC.TTF"));
        FreeTypeFontGenerator.FreeTypeFontParameter params = new
            FreeTypeFontGenerator.FreeTypeFontParameter();

        params.size = 24;
        mediumFont = generator.generateFont(params);
        generator.dispose();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        textCamera = new OrthographicCamera();
        textCamera.setToOrtho(false, 800, 480);
        textCamera.translate(-400,-240);


        setScreen(new GameScreen(this));
    }

    @Override
    public void render () {super.render(); }
    @Override
    public void dispose() { batch.dispose(); }
}
