package game.tiles.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;

public class Effect extends Entity {
    private long startTime = TimeUtils.millis();
    private Animation<TextureRegion> animation;

    public Effect(float x, float y, float width, float height, Animation<TextureRegion> animation) {
        hitbox.width = width;
        hitbox.height = height;
        hitbox.setCenter(x, y);
        this.animation = animation;
    }

    protected void update(float deltaTime) {
        if (animation.isAnimationFinished((float) TimeUtils.timeSinceMillis(startTime) / 1000)) {
            delete();
        }
    }

    protected void draw(SpriteBatch batch) {
        batch.draw(animation.getKeyFrame((float) TimeUtils.timeSinceMillis(startTime) / 1000),
                hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

}
