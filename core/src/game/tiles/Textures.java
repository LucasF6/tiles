package game.tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public final class Textures {

    public static final class Tiles {
        public static final Texture TILES_TEXTURE = new Texture("tiles.png");
        public static final TextureRegion[] TILES = getHorizontalTextureRegionArray(TILES_TEXTURE,
                0, 0, 128, 128, 4);
        public static final TextureRegion GRASS = TILES[0];
        public static final TextureRegion STONE_BACKGROUND = TILES[1];
        public static final TextureRegion STONE = TILES[2];
        public static final TextureRegion WOOD = TILES[3];

        public static final Texture NUMBERS_TEXTURE = new Texture("numbers.png");
        public static final TextureRegion[] NUMBERS = getHorizontalTextureRegionArray(NUMBERS_TEXTURE,
                0, 0, 128, 128, 9);
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
            PLAYER_DOWN.setPlayMode(Animation.PlayMode.LOOP);
            PLAYER_UP.setPlayMode(Animation.PlayMode.LOOP);
            PLAYER_RIGHT.setPlayMode(Animation.PlayMode.LOOP);
            PLAYER_LEFT.setPlayMode(Animation.PlayMode.LOOP);
        }
    }

    public static void dispose() {
        Tiles.TILES_TEXTURE.dispose();
        Tiles.NUMBERS_TEXTURE.dispose();
        Player.PLAYER_TEXTURE.dispose();
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