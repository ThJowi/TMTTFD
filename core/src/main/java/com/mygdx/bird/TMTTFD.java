package com.mygdx.bird;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class TMTTFD extends Game {

    public SpriteBatch batch, textBatch;
    public ShapeRenderer shapeRenderer;
    BitmapFont font;
    public OrthographicCamera camera, textCamera;
    public AssetManager manager;

    @Override
    public void create() {

        manager = new AssetManager();
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Dogfiles.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params.size = 24;
        font = generator.generateFont(params);
        generator.dispose();

        setScreen(new GameScreen(this));
    }

    @Override
    public void render () {super.render(); }
    @Override
    public void dispose() { batch.dispose(); }
}
