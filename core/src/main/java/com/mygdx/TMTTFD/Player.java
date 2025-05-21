package com.mygdx.TMTTFD;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Player extends WalkingCharacter {

    static final float SPEED = 120f;

    AssetManager manager;
    ButtonLayout joypad;

    Texture currentFrame;

    private int maxLives;
    private int healPower = 2;
    float animationFrame = 0;

    String direction;
    Boolean idle = true;
    Boolean attack = false;

    public Player(int initialLives, AssetManager manager) {
        super(initialLives, 3f);
        this.maxLives = initialLives;
        this.manager = manager;
        direction = "up";
        currentFrame = manager.get("character/idle/char_idle_down(1).png", Texture.class);
    }

    public void setJoypad(ButtonLayout joypad ){
        this.joypad = joypad;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (dead) {
            // Death animation
            animationFrame += 10f * delta;
            int frameTexture = (int) animationFrame + 1;
            if (frameTexture > 10)
                frameTexture = 10;
            currentFrame = manager.get("player/Dead (" + frameTexture + ").png", Texture.class);

            speed.x = 0f;
            speed.y = 0f;
        } else if (attack) {
            animationFrame += 10 * delta;
            if (animationFrame >= 6.f) {
                animationFrame -= 6.f;
                attack = false;
            }
            String aDirection = "";
            switch (direction){
                case "up":
                    aDirection = "down";
                    break;
                case "down":
                    aDirection = "up";
                    break;
                default:
                    aDirection = direction;
            }
            currentFrame = manager.get("character/attack/sword_" + aDirection + "(" + ((int) animationFrame + 1) + ").png", Texture.class);
        }
        else {

            idle = true;
            speed.x = 0f;
            speed.y = 0f;

            if (joypad.isPressed("Up")) {
                speed.y = -SPEED;
                direction = "up";
                idle = false;
            } else if (joypad.isPressed("Down")) {
                speed.y = SPEED;
                direction = "down";
                idle = false;
            } else if (joypad.isPressed("Left")) {
                speed.x = -SPEED;
                direction = "left";
                idle = false;
            } else if (joypad.isPressed("Right")) {
                speed.x = SPEED;
                direction = "right";
                idle = false;
            } else if (joypad.isPressed("Attack")){
                speed.x = 0;
                speed.y = 0;
                idle = false;
                attack = true;
                animationFrame = 0f;
            }


            switch (direction) {
                case "up":
                    if (idle){
                        if (animationFrame >= 10.f) animationFrame -= 10.f;
                        else animationFrame += 10 * delta;
                        currentFrame = manager.get("character/idle/char_idle_down(" + (int) (animationFrame/2 + 1) + ").png", Texture.class);
                    } else {
                        animationFrame += 10 * delta;
                        if (animationFrame >= 6.f){ animationFrame -= 6.f;
                        }
                        currentFrame = manager.get("character/run/char_run_down(" + ((int) animationFrame + 1) + ").png", Texture.class);
                    }
                    break;
                case "down":
                    if (idle){
                        if (animationFrame >= 10.f) animationFrame -= 10.f;
                        else animationFrame += 10 * delta;
                        currentFrame = manager.get("character/idle/char_idle_up(" + (int) (animationFrame/2 + 1) + ").png", Texture.class);
                    }
                    else {
                        animationFrame += 10 * delta;
                        if (animationFrame >= 6.f) animationFrame -= 6.f;
                        currentFrame = manager.get("character/run/char_run_up(" + ((int) animationFrame + 1) + ").png", Texture.class);
                    }
                    break;
                case "left":
                    if (idle){
                        if (animationFrame >= 10.f) animationFrame -= 10.f;
                        else animationFrame += 10 * delta;
                        currentFrame = manager.get("character/idle/char_idle_left(" + (int) (animationFrame/2 + 1) + ").png", Texture.class);
                    }
                    else {
                        animationFrame += 10 * delta;
                        if (animationFrame >= 6.f) animationFrame -= 6.f;
                        currentFrame = manager.get("character/run/char_run_left(" + ((int) animationFrame + 1) + ").png", Texture.class);
                    }
                    break;
                case "right":
                    if (idle) {
                        if (animationFrame >= 10.f) animationFrame -= 10.f;
                        else animationFrame += 10 * delta;
                        currentFrame = manager.get("character/idle/char_idle_right(" + (int) (animationFrame/2 + 1) + ").png", Texture.class);
                    }
                    else {
                        animationFrame += 10 * delta;
                        if (animationFrame >= 6.f) animationFrame -= 6.f;
                        currentFrame = manager.get("character/run/char_run_right(" + ((int) animationFrame + 1) + ").png", Texture.class);
                    }
                    break;
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        if (currentFrame != null) {
            batch.draw(
                currentFrame,
                getX() - getWidth()/2f- map.scrollX, getY() -getHeight()/2f - map.scrollY,
                getWidth(), getHeight()
            );
        }
    }

    public void drawDebug(ShapeRenderer shapes) {
        //super.drawDebug(shapes);

        shapes.begin(ShapeRenderer.ShapeType.Filled);
        shapes.setColor(Color.NAVY);
        shapes.rect(getX() - getWidth()*0.5f - map.scrollX, getY() - getHeight()*0.5f - map.scrollY, getWidth(), getHeight());
        shapes.end();
    }

    public void handleDeath(){

    }

    public void heal(){
        lives = Math.min(lives + healPower, maxLives);
    }

    public void increasMaxLives(){
        maxLives += 2;
        lives += 2;
    }

    public void increaseHealPower(){
        healPower += 2;
    }

    public int getMaxLives() {
        return maxLives;
    }

    public int getHealPower() {
        return healPower;
    }
}
