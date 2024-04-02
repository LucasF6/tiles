package game.tiles.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;
import game.tiles.items.Item;
import game.tiles.tiles.Map;

import static game.tiles.Textures.Enemy.*;

public class Alien extends SimpleEnemy {
    private float speed = 0;
    private long lastHit = TimeUtils.millis();
    private Item drop;

    public Alien(float x, float y) {
        super(0.75f, 1f, x, y, ALIEN_LEFT, ALIEN_RIGHT, ALIEN_UP);
        health = 5;
    }

    public Alien(float x, float y, Item drop) {
        this(x, y);
        this.drop = drop;
    }

    @Override
    public void update(float deltaTime) {
        float prevX = x;
        float prevY = y;
        super.update(deltaTime);
        if (!Map.getInstance().isBlocked(x - 0.75f / 2, x + 0.75f / 2, y)) {

        } else if (!Map.getInstance().isBlocked(prevX - 0.3f / 2, prevX + 0.3f / 2, y)) {
            x = prevX;
            hitbox.x = x - hitbox.width / 2;
        } else if (!Map.getInstance().isBlocked(x - 0.75f / 2, x + 0.75f / 2, prevY)) {
            y = prevY;
            hitbox.y = y;
        } else {
            x = prevX;
            y = prevY;
            hitbox.x = x - hitbox.width / 2;
            hitbox.y = y;
        }

        float distance = distanceToPlayer();
        if (0.5f < distance && distance < 10) {
            speed = 0.8f;
        } else if (distance >= 10) {
            speed = 0;
        } else if (distance < 0.4f) {
            speed = -0.2f;
        }

        if (distance < 0.9f && TimeUtils.timeSinceMillis(lastHit) > 1500) {
            hitPlayer(1.5f);
            lastHit = TimeUtils.millis();
        }
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public void onDeath() {
        if (drop != null) {
            drop.asEntity(x, y);
        }
    }

}
