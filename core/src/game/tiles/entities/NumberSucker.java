package game.tiles.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;
import game.tiles.TileValueUpdater;
import game.tiles.items.Item;

import static game.tiles.Textures.Enemy.*;

public class NumberSucker extends Entity {
    public enum Direction {
        UP,
        LEFT,
        RIGHT,
        DOWN,
        NONE;

        public static Direction[] fromIntArray(int... intArray) {
            Direction[] res = new Direction[intArray.length];
            for (int i = 0; i < intArray.length; i++) {
                switch(intArray[i]) {
                    case 0:
                        res[i] = UP;
                        break;
                    case 1:
                        res[i] = LEFT;
                        break;
                    case 2:
                        res[i] = RIGHT;
                        break;
                    case 3:
                        res[i] = DOWN;
                        break;
                    default:
                        res[i] = NONE;
                }
            }
            return res;
        }
    }

    private Animation<TextureRegion> texture;
    private Item drop;
    private final int number;
    private int i;
    private int j;
    private Direction[] directions = new Direction[] {};
    private int currentDirection = 0;
    private long wait = 1000l;
    private long startTime;
    private long lastSwitchTime;

    public NumberSucker(int x, int y, int number) {
        i = x;
        j = y;
        hitbox.x = x;
        hitbox.y = y;
        hitbox.width = 1;
        hitbox.height = 1;
        this.number = number;

        if (number < 2 || number > 5) {
            throw new IllegalArgumentException("number must be between 2 and 5 inclusive");
        }
        switch (number) {
            case 2:
                texture = NUMBER_SUCKER_TWO;
                break;
            case 3:
                texture = NUMBER_SUCKER_THREE;
                break;
            case 4:
                texture = NUMBER_SUCKER_FOUR;
                break;
            case 5:
                texture = NUMBER_SUCKER_FIVE;
                break;
        }

        startTime = TimeUtils.millis();
        lastSwitchTime = startTime;
    }

    public NumberSucker(int x, int y, int number, Item drop) {
        this(x, y, number);
        this.drop = drop;
    }

    public NumberSucker(int x, int y, int number, int... intArray) {
        this(x, y, number);
        directions = Direction.fromIntArray(intArray);
    }

    public NumberSucker(int x, int y, int number, long wait, int... intArray) {
        this(x, y, number, intArray);
        this.wait = wait;
    }

    public NumberSucker(int x, int y, int number, Item drop, int... intArray) {
        this(x, y, number, intArray);
        this.drop = drop;
    }

    public NumberSucker(int x, int y, int number, Item drop, long wait, int... intArray) {
        this(x, y, number, drop, intArray);
        this.wait = wait;
    }

    @Override
    protected void update(float deltaTime) {
        if (TileValueUpdater.getInstance().getTileValue(i, j) == number) {
            if (drop != null) {
                drop.asEntity(x + 0.5f, y + 0.5f);
            }
            TileValueUpdater.getInstance().getValueTile(i, j).zeroValue();
            delete();
        }

        if (hitbox.overlaps(Player.getInstance().hitbox)) {
            Player.getInstance().changeHealth(-40 * deltaTime);
        }

        if (directions.length != 0) {
            if (TimeUtils.timeSinceMillis(lastSwitchTime) > wait) {
                lastSwitchTime += wait;
                switch(directions[currentDirection]) {
                    case UP:
                        j++;
                        y = j;
                        hitbox.y = j;
                        break;
                    case LEFT:
                        i--;
                        hitbox.x = i;
                        break;
                    case RIGHT:
                        i++;
                        hitbox.x = i;
                        break;
                    case DOWN:
                        j--;
                        hitbox.y = j;
                        break;
                }
                currentDirection = ++currentDirection % directions.length;
            }
            switch (directions[currentDirection]) {
                case UP:
                    hitbox.y = j + (float) TimeUtils.timeSinceMillis(lastSwitchTime) / wait;
                    break;
                case LEFT:
                    hitbox.x = i - (float) TimeUtils.timeSinceMillis(lastSwitchTime) / wait;
                    break;
                case RIGHT:
                    hitbox.x = i + (float) TimeUtils.timeSinceMillis(lastSwitchTime) / wait;
                    break;
                case DOWN:
                    hitbox.y = j - (float) TimeUtils.timeSinceMillis(lastSwitchTime) / wait;
                    break;
            }
        }
    }

    @Override
    protected void draw(SpriteBatch batch) {
        batch.draw(texture.getKeyFrame((float) TimeUtils.timeSinceMillis(startTime) / 1000), hitbox.x, hitbox.y, 1, 1);
    }

}
