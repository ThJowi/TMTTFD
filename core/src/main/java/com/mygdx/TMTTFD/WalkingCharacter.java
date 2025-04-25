package com.mygdx.TMTTFD;

public class WalkingCharacter extends GameEntity {

    protected float live;
    protected boolean hit;
    protected boolean dead;

    protected float timer;
    protected float invulnerabilityTime;



    public WalkingCharacter(int live, float invulnerabilityTime){
        super();
        this.live = live;
        hit = false;
        dead = false;
        this.invulnerabilityTime = invulnerabilityTime;

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
           if (timer >= invulnerabilityTime){
               hit = false;
               timer = 0;
           }
        }

        super.act(delta);
    }

    public void reciveHit() {
        if (!hit && !dead) {
            live--;
            hit = true;
            if (live <= 0) kill();
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

    public float getLive() {
        return live;
    }

    public void setLive(float live) {
        this.live = live;
    }
}
