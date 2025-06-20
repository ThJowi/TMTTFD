package com.mygdx.TMTTFD.jsonloaders;

import java.util.ArrayList;

public class LevelJson
{
    private int mapWidth;
    private int mapHeight;

    private byte tileMap[][];
    public LevelJson()
    {
    }

    public LevelJson(int mapWidth, int mapHeight, byte[][] tileMap)
    {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.tileMap = tileMap;

    }

    public int getMapWidth() {
        return mapWidth;
    }

    public void setMapWidth(int mapWidth) {
        this.mapWidth = mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public void setMapHeight(int mapHeight) {
        this.mapHeight = mapHeight;
    }

    public byte[][] getTileMap() {
        return tileMap;
    }

    public void setTileMap(byte[][] tileMap) {
        this.tileMap = tileMap;
    }

}
