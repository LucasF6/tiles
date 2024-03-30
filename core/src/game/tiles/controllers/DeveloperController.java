package game.tiles.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.TimeUtils;

public class DeveloperController extends DefaultController {
    long lastTimeFalse = TimeUtils.millis();
    boolean ready = false;
    boolean waiting = false;

    @Override
    public boolean useItem() {
        boolean pressed = Gdx.input.isButtonPressed(Input.Buttons.RIGHT);
        if (pressed && ready) {
            ready = false;
            return true;
        } else if (!pressed && !ready && !waiting) {
            lastTimeFalse = TimeUtils.millis();
            waiting = true;
        } else if (pressed && waiting) {
            if (TimeUtils.millis() - lastTimeFalse > 50l) {
                ready = true;
            }
            waiting = false;
        }
        return false;
    }

}
