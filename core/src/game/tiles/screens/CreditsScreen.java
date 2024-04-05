package game.tiles.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import static game.tiles.Textures.MainMenu.*;
import static game.tiles.screens.Screens.GAME;
import static game.tiles.screens.Screens.MAIN_MENU_SCREEN;

public class CreditsScreen extends ScreenAdapter {
    private final Viewport viewport;
    private final Button backButton;

    public CreditsScreen() {
        viewport = new ScreenViewport();

        backButton = new Button(new Rectangle(-300, 300, 250, 100), BACK_LIT, BACK_UNLIT, this::onBack);
    }

    public void render(float delta) {
        ScreenUtils.clear(107f / 255, 244f / 255, 145f / 255, 1f);
        Vector2 vec = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        boolean clicked = Gdx.input.justTouched();
        viewport.unproject(vec);
        GAME.batch.begin();
        backButton.draw(GAME.batch, vec.x, vec.y, clicked);
        GAME.batch.end();
    }

    public void resize(int x, int y) {
        viewport.update(x, y);
        GAME.batch.setProjectionMatrix(viewport.getCamera().combined);
    }

    public void onBack() {
        GAME.setScreen(MAIN_MENU_SCREEN);
    }

}
