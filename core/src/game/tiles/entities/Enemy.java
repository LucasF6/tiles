package game.tiles.entities;

import com.badlogic.gdx.math.Rectangle;

public class Enemy extends Entity {
    protected float health;
    protected Rectangle hitbox = new Rectangle();

    public Enemy(float width, float height, float x, float y) {
        this.x = x;
        this.y = y;
        hitbox.width = width;
        hitbox.height = height;
    }

    public final void getHit(float damage) {
        health -= damage;
        if (health <= 0) {
            onDeath();
            delete();
        }
    }

    public void onDeath() {}

    public Rectangle getHitbox() {
        return hitbox;
    }
}
