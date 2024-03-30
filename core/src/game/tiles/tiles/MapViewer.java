package game.tiles.tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static game.tiles.Constants.Map.SIZE;

public class MapViewer {
    private static MapViewer instance;

    private Viewport viewport = new FitViewport(10, 10);
    private Camera camera = viewport.getCamera();

    private Rectangle visibleRectangle = new Rectangle();

    private MapViewer() {
        camera.position.set(0, 0, 0);
    }

    public static MapViewer getInstance() {
        if (instance == null) {
            instance = new MapViewer();
        }
        return instance;
    }

    public void resizeWorld(float change) {
        change *= 0.5f;
        float projectedSize = viewport.getWorldWidth() + change;
        if (projectedSize < 3.0 || projectedSize > 20.0) {
            return;
        }
        viewport.setWorldSize(viewport.getWorldWidth() + change, viewport.getWorldHeight() + change);
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Map.getInstance().setWorldSize(viewport.getWorldWidth());
    }

    public void updateCamera(float playerX, float playerY) {
        camera.position.set(playerX, playerY, 0);
        if (playerX < viewport.getWorldWidth() / 2) {
            camera.position.x = viewport.getWorldWidth() / 2;
        }
        if (playerX > SIZE - viewport.getWorldWidth() / 2) {
            camera.position.x = SIZE - viewport.getWorldWidth() / 2;
        }
        if (playerY < viewport.getWorldHeight() / 2) {
            camera.position.y = viewport.getWorldHeight() / 2;
        }
        if (playerY > SIZE - viewport.getWorldHeight() / 2) {
            camera.position.y = SIZE - viewport.getWorldHeight() / 2;
        }
        camera.update();
        visibleRectangle.width = viewport.getWorldWidth();
        visibleRectangle.height = viewport.getWorldHeight();
        visibleRectangle.x = camera.position.x - viewport.getWorldWidth() / 2;
        visibleRectangle.y = camera.position.y - viewport.getWorldHeight() / 2;
    }

    public Matrix4 getProjectionMatrix() {
        return camera.combined;
    }

    public void unproject(Vector3 vec) {
        viewport.unproject(vec);
    }

    public void setSize(int width, int height) {
        viewport.update(width, height);
    }

    public float getX() {
        return camera.position.x;
    }

    public float getY() {
        return camera.position.y;
    }

    public Rectangle getVisibleRectangle() {
        return visibleRectangle;
    }

}
