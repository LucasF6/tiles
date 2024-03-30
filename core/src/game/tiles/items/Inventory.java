package game.tiles.items;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import game.tiles.entities.ItemEntity;
import game.tiles.items.Item;

import static game.tiles.Textures.Items.*;

public class Inventory {
    private Item[] inventory = new Item[10];
    private int selectedItem = 0;

    public Inventory() {
        for (int i = 0; i < 10; i++) {
            inventory[i] = Item.NONE;
        }
    }

    public void useSelectedItem(float x, float y) {
        if (inventory[selectedItem].use(x, y)) {
            inventory[selectedItem] = Item.NONE;
        }
    }

    public void dropSelectedItem(float x, float y) {
        if (!inventory[selectedItem].getName().equals("none")) {
            inventory[selectedItem].asEntity(x, y);
            inventory[selectedItem] = Item.NONE;
        }
    }

    public void switchSelectedItem(int amount) {
        selectedItem += amount;
        selectedItem %= 10;
        if (selectedItem < 0) {
            selectedItem += 10;
        }
    }

    public void addItem(Item item) {
        if (item.isStackable()) {
            for (int i = 0; i < 10; i++) {
                if (item.getName().equals(inventory[i].getName())) {
                    inventory[i].count += item.count;
                    return;
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            if (inventory[i].getName().equals("none")) {
                inventory[i] = item;
                return;
            }
        }
    }

    public void draw(SpriteBatch batch) {
        batch.draw(INVENTORY, -3.75f, -4.5f, 7.5f, 0.75f);
        for (int i = 0; i < 10; i++) {
            if (!inventory[i].getName().equals("none")) {
                batch.draw(inventory[i].getTexture(), -3.625f + 0.75f * i, -4.375f, 0.5f, 0.5f);
                if (inventory[i].isStackable()) {
                    FONT.draw(batch, String.valueOf(inventory[i].count), -3.425f + 0.75f * i, -4.5f);
                }
            }
        }
        batch.draw(INVENTORY_SELECTION, -3.75f + 0.75f * selectedItem, -4.5f, 0.75f, 0.75f);
    }


}
