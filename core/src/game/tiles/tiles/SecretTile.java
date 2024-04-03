package game.tiles.tiles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import game.tiles.items.Item;
import game.tiles.map.Map;

import static game.tiles.Textures.Tiles.*;

public class SecretTile extends ValueTile {
    private final int number;
    private TextureRegion texture;
    private final Item item;

    public SecretTile(int number, Item item, int x, int y) {
        super(x, y);
        this.number = number;
        if (number < 2 || number > 5) {
            throw new IllegalArgumentException("tile number must be between 2 and 5 inclusive");
        }
        this.item = item;
        switch (number) {
            case 2:
                texture = SECRET_TWO;
                break;
            case 3:
                texture = SECRET_THREE;
                break;
            case 4:
                texture = SECRET_FOUR;
                break;
            case 5:
                texture = SECRET_FIVE;
                break;
        }
    }

    @Override
    public void getLeftClicked() {
        super.getLeftClicked();
        if (getValue() != number) {
            return;
        }
        Map.getInstance().setTile(new StoneBackgroundTile(x, y));
        item.asEntity(x + 0.5f, y + 0.5f);
    }

    @Override
    public TextureRegion getTexture() {
        return texture;
    }

}
