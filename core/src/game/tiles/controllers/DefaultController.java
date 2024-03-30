package game.tiles.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import game.tiles.controllers.Controller;

public class DefaultController implements Controller {


    @Override
    public boolean getLeft() {
        return Gdx.input.isKeyPressed(Input.Keys.A);
    }

    @Override
    public boolean getRight() {
        return Gdx.input.isKeyPressed(Input.Keys.D);
    }

    @Override
    public boolean getUp() {
        return Gdx.input.isKeyPressed(Input.Keys.W);
    }

    @Override
    public boolean getDown() {
        return Gdx.input.isKeyPressed(Input.Keys.S);
    }

    @Override
    public boolean getSprint() {
        return Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT);
    }

    @Override
    public boolean updateTile() {
        return Gdx.input.isButtonJustPressed(Input.Buttons.LEFT);
    }

    @Override
    public boolean useItem() {
        return Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT);
    }

    @Override
    public boolean getDrop() {
        return Gdx.input.isKeyJustPressed(Input.Keys.Q);
    }

    @Override
    public boolean zoomIn() {
        return Gdx.input.isKeyPressed(Input.Keys.P);
    }

    @Override
    public boolean zoomOut() {
        return Gdx.input.isKeyPressed(Input.Keys.O);
    }

}
