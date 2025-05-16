package com.mygdx.TMTTFD;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.TMTTFD.jsonloaders.LevelJson;

public class GameScreen implements Screen {

    TMTTFD game;
    ButtonLayout joypad;

    Stage stage;
    TileMap tileMap;

    Player player;

    public GameScreen(TMTTFD game) {
        this.game = game;

        joypad = new ButtonLayout(game.camera, game.manager, null);
        joypad.loadFromJson("joypad.json");

        tileMap = new TileMap(game.manager, game.batch);

        stage = new Stage();
        player = new Player(6, game.manager);
        player.setMap(tileMap);
        player.setJoypad(joypad);
        stage.addActor(player);
        player.setPosition(400, 240); // Posición visible y centrada
        player.setSize(48, 48);

        Viewport viewport = new Viewport() {
        };
        viewport.setCamera(game.camera);
        stage.setViewport(viewport);

        Json json = new Json();

        FileHandle file = Gdx.files.internal("Level.json");
        String scores = file.readString();
        LevelJson l = json.fromJson(LevelJson.class, scores);
        tileMap.loadFromLevel(l);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);
        game.shapeRenderer.setProjectionMatrix(game.camera.combined);
        ScreenUtils.clear(Color.SKY);

        tileMap.render();

        stage.draw();

        joypad.render(game.batch, game.textBatch);

        updateGameLogic(delta);
    }

    void updateGameLogic(float delta){

        stage.act(delta);

        tileMap.scrollX = (int) (player.getX() - 400);
        tileMap.scrollY = (int) (player.getY() - 240);
        if (tileMap.scrollX < 0)
            tileMap.scrollX = 0;
        if (tileMap.scrollY < 0)
            tileMap.scrollY = 0;
        if (tileMap.scrollX >= tileMap.width * tileMap.TILE_SIZE - 800)
            tileMap.scrollX = tileMap.width * tileMap.TILE_SIZE - 800 - 1;
        if (tileMap.scrollY >= tileMap.height * tileMap.TILE_SIZE - 480)
            tileMap.scrollY = tileMap.height * tileMap.TILE_SIZE - 480 - 1;
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
