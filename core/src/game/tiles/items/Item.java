package game.tiles.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import game.tiles.entities.ItemEntity;

public class Item {
    private final String name;

    public Item(String name) {
        this.name = name;
    }

    public void use(float x, float y) {}

    public ItemEntity asEntity(float x, float y) {
        return null;
    }

    public final String getName() {
        return name;
    }

    public TextureRegion getTexture() {
        return null;
    }

}
