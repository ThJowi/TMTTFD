package com.mygdx.TMTTFD;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class MainMenuScreen implements Screen {

    TMTTFD game;
    ButtonLayout mainMenu;

    public MainMenuScreen(TMTTFD game){
        this.game = game;

        mainMenu = new ButtonLayout(game.camera, game.manager, game.mediumFont);
        mainMenu.loadFromJson("mainmenu.json");
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);
        game.textBatch.setProjectionMatrix(game.textCamera.combined);

        game.batch.begin();
        game.batch.draw(game.manager.get("tiles/0.png", Texture.class), 0, 0, 800, 480, 0,0, 1000, 750, false, true);
        game.batch.end();

        game.textBatch.begin();
        game.bigFont.draw(game.textBatch, "The Mighty Tale\nThat Features\nDELZA", 30, 420);
        game.textBatch.end();

        mainMenu.render(game.batch, game.textBatch);

        if (mainMenu.consumeRelease("Start"))
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
