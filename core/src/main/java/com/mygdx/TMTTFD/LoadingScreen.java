package com.mygdx.TMTTFD;

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

        manager.load("tiles/0.png", Texture.class);
        manager.load("tiles/1.png", Texture.class);

        // Botones de control
        manager.load("gui/Up_off.png", Texture.class);
        manager.load("gui/Up_on.png", Texture.class);
        manager.load("gui/Down_off.png", Texture.class);
        manager.load("gui/Down_on.png", Texture.class);
        manager.load("gui/Left_off.png", Texture.class);
        manager.load("gui/Left_on.png", Texture.class);
        manager.load("gui/Right_off.png", Texture.class);
        manager.load("gui/Right_on.png", Texture.class);
        manager.load("gui/A_off.png", Texture.class);
        manager.load("gui/A_on.png", Texture.class);
        manager.load("gui/Button-on.png", Texture.class);
        manager.load("gui/Button-off.png", Texture.class);

        //Vida del jugador
        manager.load("gui/health_bar_hud.png", Texture.class);
        manager.load("gui/health_hud.png", Texture.class);


        for (int i = 0; i < 6; i++){
            manager.load("character/idle/char_idle_up("+ (i+1) + ").png", Texture.class);
            manager.load("character/idle/char_idle_down("+ (i+1) + ").png", Texture.class);
            manager.load("character/idle/char_idle_left("+ (i+1) + ").png", Texture.class);
            manager.load("character/idle/char_idle_right("+ (i+1) + ").png", Texture.class);
            manager.load("character/run/char_run_up("+ (i+1) + ").png", Texture.class);
            manager.load("character/run/char_run_left("+ (i+1) + ").png", Texture.class);
            manager.load("character/run/char_run_right("+ (i+1) + ").png", Texture.class);
            manager.load("character/run/char_run_down("+ (i+1) + ").png", Texture.class);
            manager.load("character/attack/sword_up("+ (i+1) + ").png", Texture.class);
            manager.load("character/attack/sword_left("+ (i+1) + ").png", Texture.class);
            manager.load("character/attack/sword_right("+ (i+1) + ").png", Texture.class);
            manager.load("character/attack/sword_down("+ (i+1) + ").png", Texture.class);
            manager.load("Enemy/Slime("+ (i+1) + ").png", Texture.class);
        }

        for (int i = 0; i < 10; i++){
            manager.load("character/death/death("+ (i+1) + ").png", Texture.class);
        }

        for (int i = 0; i < 8; i++){
            manager.load("Enemy/death("+ (i+1) + ").png", Texture.class);
        }


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
            game.bigFont.draw(game.textBatch, "CARREGANT...", 120, 340);
            game.mediumFont.draw(game.textBatch, (int) (loadProgress * 100.f) + "%", 360, 160);
            game.textBatch.end();

        }

        // Update step ====================================
        if(game.manager.update())
        {
            game.setScreen(new MainMenuScreen(game));
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
