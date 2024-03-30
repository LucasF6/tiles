package game.tiles.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import game.tiles.items.Item;
import game.tiles.tiles.Map;
import game.tiles.tiles.MapViewer;

public class ItemEntity extends Entity {
    private static Map map = Map.getInstance();
    private static Player player = Player.getInstance();
    private static Rectangle hitbox = new Rectangle();

    private TextureRegion texture;
    private Item item;
    private boolean attractPlayer = false;
    private float speed = 0.13f;
    private float rotationalVelocity = 1.2f;
    private float direction = MathUtils.random(0, MathUtils.PI2);

    static {
        hitbox.width = 0.5f;
        hitbox.height = 0.5f;
    }

    public ItemEntity(TextureRegion texture, Item item, float x, float y) {
        this.texture = texture;
        this.item = item;
        this.x = x;
        this.y = y;
    }

    protected final void update(float deltaTime) {
        double distanceToPlayer = Math.hypot(player.x - x, player.y - y);
        if (distanceToPlayer < 0.3) {
            player.addItem(asItem());
            delete();
        } else if (distanceToPlayer < 1.2) {
            if (attractPlayer) {
                direction = MathUtils.atan2(player.y - y, player.x - x);
                speed = 0.4f;
            } else {
                speed = 0;
            }
        } else {
            attractPlayer = true;
            direction += deltaTime * rotationalVelocity;
            speed = 0.13f;
        }
        x += deltaTime * speed * MathUtils.cos(direction);
        y += deltaTime * speed * MathUtils.sin(direction);
    }

    protected void draw(SpriteBatch batch) {
        hitbox.x = x - 0.32f;
        hitbox.y = y - 0.32f;
        if (MapViewer.getInstance().getVisibleRectangle().overlaps(hitbox)) {
            batch.draw(getTexture(), hitbox.x, hitbox.y, 0.5f, 0.5f);
        }
    }

    protected TextureRegion getTexture() {
        return texture;
    }

    protected Item asItem() {
        return item;
    }

}
