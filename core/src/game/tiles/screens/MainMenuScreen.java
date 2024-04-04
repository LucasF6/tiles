package game.tiles.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.*;

import static game.tiles.Textures.MainMenu.*;

public class MainMenuScreen extends ScreenAdapter {
    private final Tiles game;

    // private final Texture titleTexture;

    private final Viewport viewport;
    private final Button playButton;
    private final Button settingsButton;
    private final Button creditsButton;
    private final Button[] buttons;

    public MainMenuScreen(Tiles game) {
        this.game = game;
        // titleTexture = new Texture(Gdx.files.internal("title-screen.png"));
        viewport = new ScreenViewport();

        playButton = new Button(new Rectangle(-300, -125, 600, 250), PLAY_LIT, PLAY_UNLIT, this::onPlay);
        settingsButton = new Button(new Rectangle(-300, -275, 250, 100), SETTINGS_LIT, SETTINGS_UNLIT, this::onSettings);
        creditsButton = new Button(new Rectangle(50, -275, 250, 100), CREDITS_LIT, CREDITS_UNLIT, this::onCredits);
        buttons = new Button[] {playButton, settingsButton, creditsButton};
    }

    public void render(float delta) {
        ScreenUtils.clear(107f / 255, 244f / 255, 145f / 255, 1f);
        float x = Gdx.input.getX();
        float y = Gdx.input.getY();
        Vector2 vec = new Vector2(x, y);
        viewport.unproject(vec);
        x = vec.x;
        y = vec.y;

        game.batch.begin();
        for (Button button : buttons) {
            button.draw(game.batch, x, y, Gdx.input.isTouched());
        }
        game.batch.end();
    }

    public void resize(int x, int y) {
        viewport.update(x, y);
        game.batch.setProjectionMatrix(viewport.getCamera().combined);
    }

    private void onPlay() {
        game.setScreen(new GameScreen(game));
    }

    private void onSettings() {
        game.setScreen(new GameScreen(game));
    }

    private void onCredits() {
        game.setScreen(new GameScreen(game));
    }

}
