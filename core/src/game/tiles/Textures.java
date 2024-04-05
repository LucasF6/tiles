package game.tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.badlogic.gdx.graphics.g2d.Animation.PlayMode.LOOP;

public final class Textures {

    public static final class MainMenu {
        public static final Texture PLAY_TEXTURE = new Texture("play-button.png");
        public static final Texture SETTINGS_TEXTURE = new Texture("settings-button.png");
        public static final Texture CREDITS_TEXTURE = new Texture("credits-button.png");
        public static final Texture BACK_TEXTURE = new Texture("back-button.png");

        public static final TextureRegion PLAY_UNLIT = new TextureRegion(PLAY_TEXTURE, 0, 0, 600, 250);
        public static final TextureRegion PLAY_LIT = new TextureRegion(PLAY_TEXTURE, 0, 250, 600, 250);
        public static final TextureRegion SETTINGS_UNLIT = new TextureRegion(SETTINGS_TEXTURE, 0, 0, 250, 100);
        public static final TextureRegion SETTINGS_LIT = new TextureRegion(SETTINGS_TEXTURE, 0, 100, 250, 100);
        public static final TextureRegion CREDITS_UNLIT = new TextureRegion(CREDITS_TEXTURE, 0, 0, 250, 100);
        public static final TextureRegion CREDITS_LIT = new TextureRegion(CREDITS_TEXTURE, 0, 100, 250, 100);
        public static final TextureRegion BACK_UNLIT = new TextureRegion(BACK_TEXTURE, 0, 0, 250, 100);
        public static final TextureRegion BACK_LIT = new TextureRegion(BACK_TEXTURE, 0, 100, 250, 100);

        public static final Texture SLIDER_TEXTURE = new Texture("slider.png");
        public static final Texture SETTINGS_LIST = new Texture("settings-list.png");

        public static final TextureRegion[] SLIDER_REGIONS = getVerticalTextureRegionArray(SLIDER_TEXTURE,
                0, 0, 150, 75, 4);
        public static final TextureRegion SLIDER_YES_UNLIT = SLIDER_REGIONS[2];
        public static final TextureRegion SLIDER_YES_LIT = SLIDER_REGIONS[3];
        public static final TextureRegion SLIDER_NO_UNLIT = SLIDER_REGIONS[0];
        public static final TextureRegion SLIDER_NO_LIT = SLIDER_REGIONS[1];
    }

    public static final class Tiles {
        public static final Texture TILES_TEXTURE = new Texture("tiles.png");
        public static final TextureRegion[] TILES = getHorizontalTextureRegionArray(TILES_TEXTURE,
                0, 0, 128, 128, 11);
        public static final TextureRegion GRASS = TILES[0];
        public static final TextureRegion STONE_BACKGROUND = TILES[1];
        public static final TextureRegion STONE = TILES[2];
        public static final TextureRegion WOOD = TILES[3];
        public static final TextureRegion LAMP_OFF = TILES[4];
        public static final TextureRegion LAMP_ON = TILES[5];
        public static final TextureRegion SECRET_TWO = TILES[6];
        public static final TextureRegion SECRET_THREE = TILES[7];
        public static final TextureRegion SECRET_FOUR = TILES[8];
        public static final TextureRegion SECRET_FIVE = TILES[9];
        public static final TextureRegion LOCK = TILES[10];

        public static final Texture NUMBERS_TEXTURE = new Texture("numbers.png");
        public static final TextureRegion[] NUMBERS = getHorizontalTextureRegionArray(NUMBERS_TEXTURE,
                0, 0, 128, 128, 9);
    }

    public static final class Items {
        public static final Texture ITEMS_TEXTURE = new Texture("items.png");
        public static final TextureRegion[] ITEMS = getHorizontalTextureRegionArray(ITEMS_TEXTURE,
                0, 0, 64, 64, 5);
        public static final TextureRegion LAMP = ITEMS[0];
        public static final TextureRegion PICKAXE = ITEMS[1];
        public static final TextureRegion SWORD = ITEMS[2];
        public static final TextureRegion CLEANER = ITEMS[3];
        public static final TextureRegion KEY = ITEMS[4];
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

        public static final Texture NUMBER_SUCKER_TEXTURE = new Texture("number-sucker.png");
        public static final Animation<TextureRegion> NUMBER_SUCKER_TWO = new Animation<TextureRegion>(0.5f,
                getVerticalTextureRegionArray(NUMBER_SUCKER_TEXTURE, 0, 0, 128, 128, 2));
        public static final Animation<TextureRegion> NUMBER_SUCKER_THREE = new Animation<TextureRegion>(0.5f,
                getVerticalTextureRegionArray(NUMBER_SUCKER_TEXTURE, 128, 0, 128, 128, 2));
        public static final Animation<TextureRegion> NUMBER_SUCKER_FOUR = new Animation<TextureRegion>(0.5f,
                getVerticalTextureRegionArray(NUMBER_SUCKER_TEXTURE, 128 * 2, 0, 128, 128, 2));
        public static final Animation<TextureRegion> NUMBER_SUCKER_FIVE = new Animation<TextureRegion>(0.5f,
                getVerticalTextureRegionArray(NUMBER_SUCKER_TEXTURE, 128 * 3, 0, 128, 128, 2));

        static {
            ALIEN_LEFT.setPlayMode(LOOP);
            ALIEN_RIGHT.setPlayMode(LOOP);
            ALIEN_UP.setPlayMode(LOOP);

            NUMBER_SUCKER_TWO.setPlayMode(LOOP);
            NUMBER_SUCKER_THREE.setPlayMode(LOOP);
            NUMBER_SUCKER_FOUR.setPlayMode(LOOP);
            NUMBER_SUCKER_FIVE.setPlayMode(LOOP);
        }
    }

    public static final class Effects {
        public static final Texture BOOM_TEXTURE = new Texture("boom.png");
        public static final Animation<TextureRegion> BOOM = new Animation<TextureRegion>(0.25f,
                getHorizontalTextureRegionArray(BOOM_TEXTURE, 0, 0, 64, 64, 4));
    }

    public static final class Overlay {
        public static final Texture INVENTORY = new Texture("inventory.png");
        public static final Texture INVENTORY_SELECTION = new Texture("inventory-selection.png");

        public static final BitmapFont FONT = new BitmapFont();

        public static final Texture HEART_TEXTURE = new Texture("life.png");
        public static final Animation<TextureRegion> LIVING_HEART = new Animation<TextureRegion>(0.5f,
                getHorizontalTextureRegionArray(HEART_TEXTURE, 0, 0, 64, 64, 2));
        public static final TextureRegion DEAD_HEART = new TextureRegion(HEART_TEXTURE, 128, 0, 64, 64);

        static {
            FONT.setUseIntegerPositions(false);
            FONT.setColor(0, 0, 0, 1);
            FONT.getData().setScale(0.015f);

            LIVING_HEART.setPlayMode(LOOP);
        }
    }

    public static void dispose() {
        MainMenu.PLAY_TEXTURE.dispose();
        MainMenu.CREDITS_TEXTURE.dispose();
        MainMenu.SETTINGS_TEXTURE.dispose();
        MainMenu.BACK_TEXTURE.dispose();
        MainMenu.SETTINGS_TEXTURE.dispose();
        MainMenu.SLIDER_TEXTURE.dispose();
        Tiles.TILES_TEXTURE.dispose();
        Tiles.NUMBERS_TEXTURE.dispose();
        Overlay.INVENTORY.dispose();
        Overlay.INVENTORY_SELECTION.dispose();
        Items.ITEMS_TEXTURE.dispose();
        Overlay.FONT.dispose();
        Player.PLAYER_TEXTURE.dispose();
        Enemy.ALIEN_TEXTURE.dispose();
        Overlay.HEART_TEXTURE.dispose();
        Effects.BOOM_TEXTURE.dispose();
        Enemy.NUMBER_SUCKER_TEXTURE.dispose();
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
            list[i] = new TextureRegion(texture, startX, startY + i * height, width, height);
        }
        return list;
    }

}
