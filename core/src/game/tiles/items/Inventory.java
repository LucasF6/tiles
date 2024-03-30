package game.tiles.items;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import game.tiles.entities.ItemEntity;
import game.tiles.items.Item;

import static game.tiles.Textures.Items.INVENTORY;
import static game.tiles.Textures.Items.INVENTORY_SELECTION;

public class Inventory {
    private Array<Item> inventory = new Array<>(10);
    private int selectedItem = 0;

    public Inventory() {
    }

    public void useSelectedItem(int x, int y) {
        inventory.get(selectedItem).use(x, y);
    }

    public void dropSelectedItem(int x, int y) {
        inventory.removeIndex(selectedItem).asEntity(x, y);
    }

    public void switchSelectedItem(int amount) {
        selectedItem += amount;
        selectedItem %= 10;
        if (selectedItem < 0) {
            selectedItem += 10;
        }
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(INVENTORY, -3.75f, -4.5f, 7.5f, 0.75f);
        for (int i = 0; i < inventory.size; i++) {
            if (inventory.get(i) != null) {
                batch.draw(inventory.get(i).getTexture(), -3.625f + 0.75f * i, -4.375f, 0.5f, 0.5f);
            }
        }
        batch.draw(INVENTORY_SELECTION, -3.75f + 0.75f * selectedItem, -4.5f, 0.75f, 0.75f);
    }


}
