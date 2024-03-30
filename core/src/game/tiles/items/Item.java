package game.tiles.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import game.tiles.entities.ItemEntity;

public class Item {
    public static Item NONE = new Item("none", true);

    private final String name;
    private final boolean stackable;

    protected int count = 1;

    public Item(String name, boolean stackable) {
        this.name = name;
        this.stackable = stackable;
    }

    public boolean use(float x, float y) {
        return false;
    }

    public ItemEntity asEntity(float x, float y) {
        return null;
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

}
