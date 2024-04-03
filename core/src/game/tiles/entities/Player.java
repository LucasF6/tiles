package game.tiles.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;
import game.tiles.controllers.DeveloperController;
import game.tiles.items.*;
import game.tiles.map.Map;
import game.tiles.controllers.Controller;
import game.tiles.map.MapViewer;

import static game.tiles.Textures.Overlay.DEAD_HEART;
import static game.tiles.Textures.Overlay.LIVING_HEART;
import static game.tiles.Textures.Player.*;

public class Player extends Entity {
    private static final float NORMAL_SPEED = 2;
    private static final float FAST_SPEED = 5;
    private static final float DIAGONAL_SCALAR = (float) Math.pow(0.5, 0.5);

    private static Player instance;

    private Inventory inventory = new Inventory();
    private float health = 10;

    private Animation<TextureRegion> animation = PLAYER_DOWN;
    private Controller controller = new DeveloperController();
    private final long startTime = TimeUtils.millis();

    private Player() {
        x = 1;
        y = 1;
        hitbox.x = x - 0.3f / 2;
        hitbox.y = y;
        hitbox.width = 0.3f;
        hitbox.height = 0.3f;

        inventory.addItem(new Cleaner());
        inventory.addItem(new Pickaxe());
        inventory.addItem(new Sword());
        inventory.addItem(new Key("key"));
    }

    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
            System.out.println("player returned");
        }
        return instance;
    }

    @Override
    public void update(float deltaTime) {
        if (controller.getDrop()) {
            inventory.dropSelectedItem(x, y - 0.4f);
        }

        if (controller.updateTile()) {
            Vector3 vec = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            MapViewer.getInstance().unproject(vec);
            Map.getInstance().getLeftClicked((int) Math.floor(vec.x), (int) Math.floor(vec.y));
        }

        if (controller.useItem()) {
            Vector3 vec = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            MapViewer.getInstance().unproject(vec);
            inventory.useSelectedItem(vec.x, vec.y);
        }

        boolean zoomIn = controller.zoomIn();
        boolean zoomOut = controller.zoomOut();
        if (zoomIn || zoomOut) {
            MapViewer.getInstance().resizeWorld(
                    deltaTime * ((zoomIn ? -4.0f : 0) + (zoomOut ? 4.0f : 0)));
        }

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
        if (!Map.getInstance().isBlocked(projX - 0.3f / 2, projX + 0.3f / 2, projY)) {
            x = projX;
            y = projY;
        } else if (!Map.getInstance().isBlocked(x - 0.3f / 2, x + 0.3f / 2, projY)) {
            y = projY;
        } else if (!Map.getInstance().isBlocked(projX - 0.3f / 2, projX + 0.3f / 2, y)) {
            x = projX;
        }

        hitbox.x = x - 0.3f / 2;
        hitbox.y = y;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(getTextureRegion(), x - 0.75f / 2, y, 0.75f, 1f);
    }

    public void drawInventory(SpriteBatch batch) {
        inventory.draw(batch);
    }

    public void drawHealth(SpriteBatch batch) {
        for (int i = 0; i < 10; i++) {
            if (i <= health) {
                batch.draw(LIVING_HEART.getKeyFrame((float) TimeUtils.timeSinceMillis(startTime) / 1000 + 0.1f * i), -5, -5 + 0.5f * i, 0.5f, 0.5f);
            } else {
                batch.draw(DEAD_HEART, -5, -5 + 0.5f * i, 0.5f, 0.5f);
            }
        }
    }

    public float getHealth() {
        return health;
    }

    public void changeHealth(float amount) {
        health += amount;
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

    public void useSelectedItem(float x, float y) {
        inventory.useSelectedItem(x, y);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
