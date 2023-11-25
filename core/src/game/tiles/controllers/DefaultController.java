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

}
