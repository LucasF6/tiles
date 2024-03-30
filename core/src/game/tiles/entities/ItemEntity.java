package game.tiles.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;
import game.tiles.items.Item;
import game.tiles.tiles.Map;
import game.tiles.tiles.MapViewer;

public class ItemEntity extends Entity {
    private static Map map = Map.getInstance();
    private static Player player = Player.getInstance();

    private TextureRegion texture;
    private Item item;
    private float speed = 0.13f;
    private float rotationalVelocity = 1.2f;
    private float direction = MathUtils.random(0, MathUtils.PI2);

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
            direction = MathUtils.atan2(player.y - y, player.x - x);
            speed = 0.4f;
        } else {
            direction += deltaTime * rotationalVelocity;
            speed = 0.13f;
        }
        float projX = x + deltaTime * speed * MathUtils.cos(direction);
        float projY = y + deltaTime * speed * MathUtils.sin(direction);
        if (!map.isBlocked(projX, projY)) {
            x = projX;
            y = projY;
        } else if (!map.isBlocked(x, projY)) {
            y = projY;
        } else if (!map.isBlocked(projX, y)) {
            x = projX;
        }
    }

    protected void draw(SpriteBatch batch) {
        if (MapViewer.getInstance().getVisibleRectangle().contains(x, y)) {
            batch.draw(getTexture(), x - 0.32f, y - 0.32f, 0.5f, 0.5f);
        }
    }

    protected TextureRegion getTexture() {
        return texture;
    }

    protected Item asItem() {
        return item;
    }

}
