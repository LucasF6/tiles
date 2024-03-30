package game.tiles.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * An enemy with an animation and that is controlled through direction and speed
 */
public class SimpleEnemy extends Enemy {
    private static Player player = Player.getInstance();

    protected float speed;
    private TextureRegion texture;
    private final Animation<TextureRegion> left;
    private final Animation<TextureRegion> right;
    private final Animation<TextureRegion> up;
    private final long startTime = TimeUtils.millis();

    public SimpleEnemy(float width, float height, float x, float y,
                       Animation<TextureRegion> left, Animation<TextureRegion> right, Animation<TextureRegion> up) {
        super(width, height, x, y);
        this.left = left;
        this.right = right;
        this.up = up;
    }

    @Override
    public void update(float deltaTime) {
        float direction = getDirection();
        float stateTime = (float) TimeUtils.timeSinceMillis(startTime) / 1000;
        if (direction > 0) {
            texture = up.getKeyFrame(stateTime);
        } else if (direction < -MathUtils.HALF_PI) {
            texture = left.getKeyFrame(stateTime);
        } else {
            texture = right.getKeyFrame(stateTime);
        }

        float speed = getSpeed();
        x += deltaTime * speed * MathUtils.cos(direction);
        y += deltaTime * speed * MathUtils.sin(direction);
        hitbox.x = x;
        hitbox.y = y;
    }

    protected float getDirection() {
        return getDirectionToPlayer();
    }

    protected final float getDirectionToPlayer() {
        return MathUtils.atan2(player.y - y, player.x - x);
    }

    protected float getSpeed() {
        return 0;
    }

    protected void draw(SpriteBatch batch) {
        batch.draw(texture, x, y, hitbox.width, hitbox.height);
    }



}
