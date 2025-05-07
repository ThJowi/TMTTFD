package com.mygdx.TMTTFD;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Player extends WalkingCharacter {

    static final float SPEED = 240f;

    AssetManager manager;
    ButtonLayout joypad;

    Texture currentFrame;

    private int maxLives;
    private int healPower = 2;
    float animationFrame = 0;

    public Player(int initialLives, AssetManager manager) {
        super(initialLives, 3f);
        this.maxLives = initialLives;
        this.manager = manager;
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
        } else {

            String direction = null;
            if (joypad.isPressed("Up")) {
                speed.y = SPEED;
                direction = "up";
            } else if (joypad.isPressed("Down")) {
                speed.y = -SPEED;
                direction = "down";
            } else if (joypad.isPressed("Left")) {
                speed.x = -SPEED;
                direction = "left";
            } else if (joypad.isPressed("Right")) {
                speed.x = SPEED;
                direction = "right";
            }

            switch (direction) {
                case "up":
                    animationFrame += 10 * delta;
                    if (animationFrame >= 6.f) animationFrame -= 6.f;
                    currentFrame = manager.get("charcater/run_up(" + ((int) animationFrame + 1) + ").png", Texture.class);
                    break;
                case "down":
                    animationFrame += 10 * delta;
                    if (animationFrame >= 6.f) animationFrame -= 6.f;
                    currentFrame = manager.get("charcater/run_down(" + ((int) animationFrame + 1) + ").png", Texture.class);
                    break;
                case "left":
                    animationFrame += 10 * delta;
                    if (animationFrame >= 6.f) animationFrame -= 6.f;
                    currentFrame = manager.get("charcater/run_left(" + ((int) animationFrame + 1) + ").png", Texture.class);
                    break;
                case "right":
                    animationFrame += 10 * delta;
                    if (animationFrame >= 6.f) animationFrame -= 6.f;
                    currentFrame = manager.get("charcater/run_right(" + ((int) animationFrame + 1) + ").png", Texture.class);
                    break;

            }

            // Idle animation (temporaria hasta nuevo movimiento)

            if (animationFrame >= 6.f) animationFrame -= 10 * delta;
            else animationFrame += 10 * delta;
            currentFrame = manager.get("character/char_idle_down(" + (int) (animationFrame + 1) + ").png", Texture.class);
        }
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
