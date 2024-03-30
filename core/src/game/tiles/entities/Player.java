package game.tiles.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;
import game.tiles.items.Inventory;
import game.tiles.items.Item;
import game.tiles.tiles.Map;
import game.tiles.controllers.Controller;
import game.tiles.controllers.DefaultController;

import static game.tiles.Textures.Player.*;

public class Player extends Entity {
    private static final float NORMAL_SPEED = 2; // 2
    private static final float FAST_SPEED = 5;   // 5
    private static final float DIAGONAL_SCALAR = (float) Math.pow(0.5, 0.5);

    private static Player instance;

    private Inventory inventory = new Inventory();

    private Animation<TextureRegion> animation = PLAYER_DOWN;
    private Controller controller = new DefaultController();
    private final long startTime = TimeUtils.millis();

    private final Map map = Map.getInstance();

    private Player() {
        x = 1;
        y = 1;
    }

    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }

    @Override
    public void update(float deltaTime) {
        boolean left = controller.getLeft(); boolean right = controller.getRight();
        boolean up = controller.getUp(); boolean down = controller.getDown();
        float s = controller.getSprint() ? FAST_SPEED : NORMAL_SPEED;
        float projX = x;
        float projY = y;
        if ((left || right) && (up || down)) {
            s *= DIAGONAL_SCALAR;
        }
        if (up && !down) {
            projY += s * deltaTime;
            animation = PLAYER_UP;
        }
        if (down && !up) {
            projY -= s * deltaTime;
            animation = PLAYER_DOWN;
        }
        if (left && !right) {
            projX -= s * deltaTime;
            animation = PLAYER_LEFT;
        }
        if (right && !left) {
            projX += s * deltaTime;
            animation = PLAYER_RIGHT;
        }
        if (!map.isBlocked(projX - 0.3f / 2, projX + 0.3f / 2, projY)) {
            x = projX;
            y = projY;
        } else if (!map.isBlocked(x - 0.3f / 2, x + 0.3f / 2, projY)) {
            y = projY;
        } else if (!map.isBlocked(projX - 0.3f / 2, projX + 0.3f / 2, y)) {
            x = projX;
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(getTextureRegion(), x - 0.75f / 2, y, 0.75f, 1f);
    }

    public void drawInventory(SpriteBatch batch) {
        inventory.draw(batch);
    }

    public TextureRegion getTextureRegion() {
        return animation.getKeyFrame((float) TimeUtils.timeSinceMillis(startTime) / 1000);
    }

    public void addItem(Item item) {
        inventory.addItem(item);
    }

    public void switchSelectedItem(int amount) {
        inventory.switchSelectedItem(amount);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
