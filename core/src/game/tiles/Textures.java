package game.tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP;

public final class Textures {

    public static final class Tiles {
        public static final Texture TILES_TEXTURE = new Texture("tiles.png");
        public static final TextureRegion[] TILES = getHorizontalTextureRegionArray(TILES_TEXTURE,
                0, 0, 128, 128, 10);
        public static final TextureRegion GRASS = TILES[0];
        public static final TextureRegion STONE_BACKGROUND = TILES[1];
        public static final TextureRegion STONE = TILES[2];
        public static final TextureRegion WOOD = TILES[3];
        public static final TextureRegion LAMP_OFF = TILES[4];
        public static final TextureRegion LAMP_ON = TILES[5];
        public static final TextureRegion LOCKED_TWO = TILES[6];
        public static final TextureRegion LOCKED_THREE = TILES[7];
        public static final TextureRegion LOCKED_FOUR = TILES[8];
        public static final TextureRegion LOCKED_FIVE = TILES[9];

        public static final Texture NUMBERS_TEXTURE = new Texture("numbers.png");
        public static final TextureRegion[] NUMBERS = getHorizontalTextureRegionArray(NUMBERS_TEXTURE,
                0, 0, 128, 128, 9);
    }

    public static final class Items {
        public static final Texture INVENTORY = new Texture("inventory.png");
        public static final Texture INVENTORY_SELECTION = new Texture("inventory-selection.png");

        public static final BitmapFont FONT = new BitmapFont();

        public static final Texture ITEMS_TEXTURE = new Texture("items.png");
        public static final TextureRegion[] ITEMS = getHorizontalTextureRegionArray(ITEMS_TEXTURE,
                0, 0, 64, 64, 3);
        public static final TextureRegion LAMP = ITEMS[0];
        public static final TextureRegion PICKAXE = ITEMS[1];
        public static final TextureRegion SWORD = ITEMS[2];

        static {
            FONT.setUseIntegerPositions(false);
            FONT.setColor(0, 0, 0, 1);
            FONT.getData().setScale(0.015f);
        }
    }

    public static final class Player {
        public static final Texture PLAYER_TEXTURE = new Texture("player.png");
        public static final Animation<TextureRegion> PLAYER_DOWN = new Animation<TextureRegion>(0.25f,
                getHorizontalTextureRegionArray(PLAYER_TEXTURE, 0, 0, 96, 128, 4));
        public static final Animation<TextureRegion> PLAYER_LEFT = new Animation<TextureRegion>(0.25f,
                getHorizontalTextureRegionArray(PLAYER_TEXTURE, 0, 128, 96, 128, 4));
        public static final Animation<TextureRegion> PLAYER_RIGHT = new Animation<TextureRegion>(0.25f,
                getHorizontalTextureRegionArray(PLAYER_TEXTURE, 0, 128 * 2, 96, 128, 4));
        public static final Animation<TextureRegion> PLAYER_UP = new Animation<TextureRegion>(0.25f,
                getHorizontalTextureRegionArray(PLAYER_TEXTURE, 0, 128 * 3, 96, 128, 4));

        static {
            PLAYER_DOWN.setPlayMode(LOOP);
            PLAYER_UP.setPlayMode(LOOP);
            PLAYER_RIGHT.setPlayMode(LOOP);
            PLAYER_LEFT.setPlayMode(LOOP);
        }
    }

    public static final class Enemy {
        public static final Texture ALIEN_TEXTURE = new Texture("alien.png");
        public static final Animation<TextureRegion> ALIEN_LEFT = new Animation<TextureRegion>(0.25f,
                getHorizontalTextureRegionArray(ALIEN_TEXTURE, 0, 0, 96, 128, 4));
        public static final Animation<TextureRegion> ALIEN_RIGHT = new Animation<TextureRegion>(0.25f,
                getHorizontalTextureRegionArray(ALIEN_TEXTURE, 0, 128, 96, 128, 4));
        public static final Animation<TextureRegion> ALIEN_UP = new Animation<TextureRegion>(0.25f,
                getHorizontalTextureRegionArray(ALIEN_TEXTURE, 0, 128 * 2, 96, 128, 4));

        static {
            ALIEN_LEFT.setPlayMode(LOOP);
            ALIEN_RIGHT.setPlayMode(LOOP);
            ALIEN_UP.setPlayMode(LOOP);
        }
    }

    public static void dispose() {
        Tiles.TILES_TEXTURE.dispose();
        Tiles.NUMBERS_TEXTURE.dispose();
        Items.INVENTORY.dispose();
        Items.INVENTORY_SELECTION.dispose();
        Items.ITEMS_TEXTURE.dispose();
        Items.FONT.dispose();
        Player.PLAYER_TEXTURE.dispose();
        Enemy.ALIEN_TEXTURE.dispose();
    }

    public static TextureRegion[] getHorizontalTextureRegionArray(Texture texture, int startX, int startY,
                                                                  int width, int height, int num) {
        if (num < 0) {
            throw new IllegalArgumentException("number of times can't be negative");
        }
        TextureRegion[] list = new TextureRegion[num];
        for (int i = 0; i < num; i++) {
            list[i] = new TextureRegion(texture, startX + i * width, startY, width, height);
        }
        return list;
    }

    public static TextureRegion[] getVerticalTextureRegionArray(Texture texture, int startX, int startY,
                                                                  int width, int height, int num) {
        if (num < 0) {
            throw new IllegalArgumentException("number of times can't be negative");
        }
        TextureRegion[] list = new TextureRegion[num];
        for (int i = 0; i < num; i++) {
            list[i] = new TextureRegion(texture, startX + width, startY + i * height, width, height);
        }
        return list;
    }

}
