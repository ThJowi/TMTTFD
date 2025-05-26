package com.mygdx.TMTTFD;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class WinScreen implements Screen {

    TMTTFD game;
    ButtonLayout winMenu;

    public WinScreen(TMTTFD game){
        this.game = game;

        winMenu = new ButtonLayout(game.camera, game.manager, game.mediumFont);
        winMenu.loadFromJson("winmenu.json");
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
        game.bigFont.draw(game.textBatch,"ENHORABONA!", 100, 480 - 60);
        game.smallFont.draw(game.textBatch,"Enemic   derrotat!", 120, 480 - 420);
        game.textBatch.end();

        winMenu.render(game.batch, game.textBatch);


        if(winMenu.consumeRelease("Menu"))
        {
            this.dispose();
            game.setScreen(new MainMenuScreen(game));
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
