package game.tiles.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import game.tiles.map.MapViewer;

public class Entity {
    private static Array<Entity> entities = new Array<>();
    private static SpriteBatch batch;

    protected float x;
    protected float y;
    protected Rectangle hitbox = new Rectangle();

    public Entity() {
        entities.add(this);
    }

    protected void update(float deltaTime) {}

    protected void draw(SpriteBatch batch) {}

    public final void delete() {
        entities.removeValue(this, true);
    }

    public boolean overlaps(float x, float y) {
        return hitbox.contains(x, y);
    }

    public float distanceToPlayer() {
        return (float) Math.hypot(Player.getInstance().x - x, Player.getInstance().y - y);
    }

    public static void updateAll(float deltaTime) {
        for (Entity entity : entities) {
            entity.update(deltaTime);
        }
    }

    public static Array<Entity> getEntities() {
        return entities;
    }

    public static void setBatch(SpriteBatch batch) {
        Entity.batch = batch;
    }

    public static void drawAll() {
        for (Entity entity : entities) {
            if (MapViewer.getInstance().getVisibleRectangle().overlaps(entity.hitbox)) {
                entity.draw(batch);
            }
        }
    }

}
