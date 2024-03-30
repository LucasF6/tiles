package game.tiles.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import game.tiles.tiles.Map;

import static game.tiles.Textures.Enemy.*;

public class Alien extends SimpleEnemy {
    public Alien(float x, float y) {
        super(0.75f, 1f, x, y, ALIEN_LEFT, ALIEN_RIGHT, ALIEN_UP);
        health = 5;
    }

    @Override
    public void update(float deltaTime) {
        float prevX = x;
        float prevY = y;
        super.update(deltaTime);
        if (!Map.getInstance().isBlocked(x - 0.3f / 2, x + 0.3f / 2, y)) {

        } else if (!Map.getInstance().isBlocked(prevX - 0.3f / 2, prevX + 0.3f / 2, y)) {
            x = prevX;
            hitbox.x = x;
        } else if (!Map.getInstance().isBlocked(x - 0.3f / 2, x + 0.3f / 2, prevY)) {
            y = prevY;
            hitbox.y = y;
        } else {
            x = prevX;
            y = prevY;
            hitbox.x = x;
            hitbox.y = y;
        }
    }

    @Override
    public float getSpeed() {
        float distance = distanceToPlayer();
        return 0.5f < distance && distance < 10 ? 0.8f : 0;
    }

}
