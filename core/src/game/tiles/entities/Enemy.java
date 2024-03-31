package game.tiles.entities;

public class Enemy extends Entity implements Attackable {
    protected float health;

    public Enemy(float width, float height, float x, float y) {
        this.x = x;
        this.y = y;
        hitbox.width = width;
        hitbox.height = height;
    }

    @Override
    public final void getHit(float damage) {
        health -= damage;
        if (health <= 0) {
            onDeath();
            delete();
        }
    }

    protected final void hitPlayer(float damage) {
        Player.getInstance().changeHealth(-damage);
    }

    public void onDeath() {}

}
