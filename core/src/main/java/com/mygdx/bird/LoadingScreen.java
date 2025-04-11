package com.mygdx.bird;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class LoadingScreen implements Screen {

    TMTTFD game;
    float loadProgress;

    LoadingScreen(TMTTFD game){
        this.game = game;
        AssetManager manager = game.manager;

        manager.load("BG.png", Texture.class);
        manager.load("tiles/0.png", Texture.class);

        // Botones de control
        manager.load("gui/Up_off.png", Texture.class);
        manager.load("gui/Up_on.png", Texture.class);
        manager.load("gui/Down_off.png", Texture.class);
        manager.load("gui/Down_on.png", Texture.class);
        manager.load("gui/Left_off.png", Texture.class);
        manager.load("gui/Left_on.png", Texture.class);
        manager.load("gui/Right_off.png", Texture.class);
        manager.load("gui/Right_on.png", Texture.class);

        manager.finishLoading();

        loadProgress = 0f;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        float currentLoadProgress = game.manager.getProgress();
        if(currentLoadProgress > loadProgress + 0.05f)
        {
            loadProgress = currentLoadProgress;

            game.camera.update();
            game.batch.setProjectionMatrix(game.camera.combined);
            game.textBatch.setProjectionMatrix(game.textCamera.combined);
            game.shapeRenderer.setProjectionMatrix(game.camera.combined);

            ScreenUtils.clear(Color.BLACK);

            // Progress bar
            game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            game.shapeRenderer.setColor(Color.YELLOW);
            game.shapeRenderer.rect(90, 290, 620, 100);
            game.shapeRenderer.setColor(Color.BLACK);
            game.shapeRenderer.rect(100, 300, 600, 80);
            game.shapeRenderer.setColor(Color.ORANGE);
            game.shapeRenderer.rect(110, 310, 580 * loadProgress, 60);
            game.shapeRenderer.end();

            game.textBatch.begin();
            game.font.draw(game.textBatch, "CARREGANT...", 120, 340);
            game.font.draw(game.textBatch, (int) (loadProgress * 100.f) + "%", 360, 160);
            game.textBatch.end();

        }

        // Update step ====================================
        if(game.manager.update())
        {
            game.setScreen(new GameScreen(game));
            this.dispose();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
