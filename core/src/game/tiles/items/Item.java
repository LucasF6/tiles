package game.tiles.items;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import game.tiles.entities.ItemEntity;
import game.tiles.entities.Player;

public class Item {
    public static Item NONE = new Item("none", true);

    private final Sprite sprite = new Sprite();
    private final String name;
    private final boolean stackable;

    protected int count = 1;
    protected float angleRadiansOffset = -MathUtils.HALF_PI;

    public Item(String name, boolean stackable) {
        this.name = name;
        this.stackable = stackable;
        sprite.setBounds(0, 0, 0.5f, 0.5f);
    }

    public boolean use(float x, float y) {
        return false;
    }

    public ItemEntity asEntity(float x, float y) {
        return new ItemEntity(getTexture(), this, x, y);
    }

    public final String getName() {
        return name;
    }

    public final boolean isStackable() {
        return stackable;
    }

    public TextureRegion getTexture() {
        return null;
    }

    public void drawSprite(SpriteBatch batch, float angleRadians) {
        sprite.setRegion(getTexture());
        sprite.setCenter(
                Player.getInstance().getX() + 0.35f * MathUtils.cos(angleRadians),
                Player.getInstance().getY() + 0.35f * MathUtils.sin(angleRadians) + 0.3f);
        sprite.setOriginCenter();
        sprite.setRotation((angleRadians + angleRadiansOffset) * MathUtils.radiansToDegrees);
        sprite.draw(batch);
    }

}
