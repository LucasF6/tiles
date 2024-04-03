package game.tiles.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import game.tiles.Textures;
import game.tiles.controllers.Controller;
import game.tiles.controllers.DefaultController;
import game.tiles.screens.MainMenuScreen;

public class Tiles extends Game {
    public SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();

        setScreen(new MainMenuScreen(this));
    }

    @Override
    public void dispose () {
        batch.dispose();
        Textures.dispose();
    }

}
