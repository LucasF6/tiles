package game.tiles.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class Entity {
    private static Array<Entity> entities = new Array<>();
    private static SpriteBatch batch;

    protected float x;
    protected float y;

    public Entity() {
        entities.add(this);
    }

    protected void update(float deltaTime) {}

    protected void draw(SpriteBatch batch) {}

    public final void delete() {
        entities.removeValue(this, true);
    }

    public static void updateAll(float deltaTime) {
        for (Entity entity : entities) {
            entity.update(deltaTime);
        }
    }

    public static void setBatch(SpriteBatch batch) {
        Entity.batch = batch;
    }

    public static void drawAll() {
        for (Entity entity : entities) {
            entity.draw(batch);
        }
    }

}
