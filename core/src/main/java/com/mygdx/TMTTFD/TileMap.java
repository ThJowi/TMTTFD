package com.mygdx.TMTTFD;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.TMTTFD.jsonloaders.LevelJson;

public class TileMap {

    public static final int TILE_SIZE = 48;
    int width;
    int height;
    byte tiles[][];
    AssetManager manager;
    SpriteBatch batch;

    public int scrollX, scrollY;

    public TileMap(AssetManager manager, SpriteBatch batch)
    {
        this.manager = manager;
        this.batch = batch;
    }

    void loadFromLevel(LevelJson l)
    {
        // Load from json file
        width = l.getMapWidth();
        height = l.getMapHeight();

        tiles = new byte[height][];

        for(int i = 0; i < height; i++)
        {
            tiles[i] = new byte[width];
        }

        for(int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                tiles[i][j] = l.getTileMap()[i][j];
            }
        }
    }

    public void render()
    {

        batch.begin();

        // Tile map
        for(int j = 0; j < height; j++)
            for(int i = 0; i < width; i++)
            {
                batch.draw(manager.get("tiles/"+tiles[j][i]+".png", Texture.class),
                    TILE_SIZE * i - scrollX,
                    TILE_SIZE * j - scrollY,
                    TILE_SIZE, TILE_SIZE,
                    0, 0, 128, 128,
                    false, true);
            }
        batch.end();
    }

    boolean isSolid(int x, int y)
    {
        int mapX = x / TILE_SIZE;
        int mapY = y / TILE_SIZE;

        if(mapX < 0) mapX = 0;
        if(mapY < 0) mapY = 0;
        if(mapX >= width) mapX = width - 1;
        if(mapY >= height) mapY = height - 1;

        return tiles[mapY][mapX] != 0 && tiles[mapY][mapX] > 0;
    }
}
