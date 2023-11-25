package game.tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.TimeUtils;
import game.tiles.controllers.Controller;

import static game.tiles.Textures.Player.*;

public class Player {
    private static final float NORMAL_SPEED = 2;
    private static final float FAST_SPEED = 5;
    private static final float DIAGONAL_SCALAR = (float) Math.pow(0.5, 0.5);

    private Animation<TextureRegion> animation = PLAYER_DOWN;
    private float x = 1;
    private float y = 1;
    private final long startTime = TimeUtils.millis();

    public void update(Controller controller, Map map) {
        float delta = Gdx.graphics.getDeltaTime();
        boolean left = controller.getLeft(); boolean right = controller.getRight();
        boolean up = controller.getUp(); boolean down = controller.getDown();
        float s = controller.getSprint() ? FAST_SPEED : NORMAL_SPEED;
        float projX = x;
        float projY = y;
        if ((left || right) && (up || down)) {
            s *= DIAGONAL_SCALAR;
        }
        if (up && !down) {
            projY += s * delta;
            animation = PLAYER_UP;
        }
        if (down && !up) {
            projY -= s * delta;
            animation = PLAYER_DOWN;
        }
        if (left && !right) {
            projX -= s * delta;
            animation = PLAYER_LEFT;
        }
        if (right && !left) {
            projX += s * delta;
            animation = PLAYER_RIGHT;
        }
        if (map.allowPosition(projX, projY) || !map.allowPosition(x, y)) {
            x = projX;
            y = projY;
        } else if (map.allowPosition(x, projY)) {
            y = projY;
        } else if (map.allowPosition(projX, y)) {
            x = projX;
        }
    }

    public TextureRegion getTextureRegion() {
        return animation.getKeyFrame((float) TimeUtils.timeSinceMillis(startTime) / 1000);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
