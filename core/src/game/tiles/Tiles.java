package game.tiles;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import game.tiles.controllers.Controller;
import game.tiles.controllers.DefaultController;

public class Tiles extends Game {
    public SpriteBatch batch;
    public Controller controller = new DefaultController();

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
