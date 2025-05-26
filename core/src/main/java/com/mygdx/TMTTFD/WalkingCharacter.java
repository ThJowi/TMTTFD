package com.mygdx.TMTTFD;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class WalkingCharacter extends GameEntity {

    protected int lives;
    protected boolean hit;
    protected boolean dead;

    protected float animationFrame = 0f;

    protected float timer;
    protected float invulnerabilityTime;

    protected boolean visible;
    protected  float blinkTimer;
    protected static final float BLINK_INTERVAL = 0.1f;

    public WalkingCharacter(int lives, float invulnerabilityTime){
        super();
        this.lives = lives;
        hit = false;
        dead = false;
        this.invulnerabilityTime = invulnerabilityTime;
        visible = true;
    }

    @Override
    public void act(float delta){

        float nextX = getX() + delta * speed.x;
        float nextY = getY() + delta * speed.y;

        if (!map.isSolid((int)(nextX - getWidth()/2), (int)getY()) &&
            !map.isSolid((int)(nextX + getWidth()/2), (int)getY()))
        {
            moveBy(delta * speed.x, 0);
        } else
        {
            speed.x = 0;
        }

        if (!map.isSolid((int)getX(), (int)(nextY - getHeight()/2)) &&
            !map.isSolid((int)getX(), (int)(nextY + getHeight()/2)))
        {
            moveBy(0, delta * speed.y);
        } else
        {
            speed.y = 0;
        }

        if (hit){
           timer += delta;
           blinkTimer += delta;

           if (blinkTimer >= BLINK_INTERVAL){
               visible = !visible;
               blinkTimer = 0f;
           }
           if (timer >= invulnerabilityTime){
               hit = false;
               timer = 0;
               visible = true;
           }
        }

        super.act(delta);
    }

    @Override
    public void drawDebug(ShapeRenderer shapes) {
        super.drawDebug(shapes);

        shapes.begin(ShapeRenderer.ShapeType.Filled);
        shapes.setColor(Color.NAVY);
        shapes.rect(getX() - getWidth()*0.5f - map.scrollX, getY() - getHeight()*0.5f - map.scrollY, getWidth(), getHeight());
        shapes.end();
    }

    public void reciveHit(float sourceX, float sourceY) {
        if (!hit && !dead) {
            lives--;
            hit = true;

            float dx = getX() - sourceX;
            float dy = getY() - sourceY;

            if (dx > dy) dx = 0;
            else dy = 0;

            Vector2 knockback = new Vector2(dx, dy).nor().scl(1000f);

            speed.set(knockback);

            if (lives <= 0) kill();
        }
    }

    public void kill(){
        dead = true;
        speed.set(0,0);
    }

    public boolean gotHit() {
        return hit;
    }

    public boolean isDead() {
        return dead;
    }

    public int getLive() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}
