package game.tiles.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.*;
import game.tiles.Tiles;

public class MainMenuScreen extends ScreenAdapter {
    private final Tiles game;

    private final Texture titleTexture;

    private final Viewport viewport;

    public MainMenuScreen(Tiles game) {
        this.game = game;
        titleTexture = new Texture(Gdx.files.internal("title-screen.png"));
        viewport = new FitViewport(1, 1);
    }

    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
        ScreenUtils.clear(107f / 255, 244f / 255, 145f / 255, 1f);

        game.batch.begin();
        game.batch.draw(titleTexture, -0.5f, -0.5f, 1, 1);
        game.batch.end();
    }

    public void resize(int x, int y) {
        viewport.update(x, y);
        game.batch.setProjectionMatrix(viewport.getCamera().combined);
    }

    public void dispose() {
        titleTexture.dispose();
    }

}
