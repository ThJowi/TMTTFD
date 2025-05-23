package com.mygdx.TMTTFD;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;


public class Slime extends WalkingCharacter{


    private Player target;
    private static final float SPEED = 40f;

    private Texture currentFrame;
    private AssetManager manager;
    float animationFrame = 0;

    public Slime(Player target, int lives, float invulnerabilityTime, AssetManager manager) {
        super(lives, invulnerabilityTime);
        this.target = target;
        this.manager = manager;
        currentFrame = manager.get("Enemy/Slime(1).png", Texture.class);
    }

    @Override
    public void act(float delta){
        super.act(delta);
        float distanceY = target.getY() - getY();
       float distanceX = target.getX() - getX();

        if (Math.abs(distanceY) > Math.abs(distanceX)){
            speed.x = 0;
            speed.y = Math.signum(distanceY) * SPEED;
        }
        else {
            speed.y = 0;
            speed.x = Math.signum(distanceX) * SPEED;
        }

        animationFrame += delta * 10;
        if (animationFrame >= 5f) animationFrame -= 5f;
        currentFrame = manager.get("Enemy/Slime(" + (int) (animationFrame+1) + ").png", Texture.class);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        if (currentFrame != null) {
            batch.draw(
                currentFrame,
                getX() - getWidth()/2f - map.scrollX, getY() - getHeight()/2f - map.scrollY,
                getWidth(), getHeight()
            );
        }
    }
}
