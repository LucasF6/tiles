package game.tiles.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.util.function.Consumer;

import static game.tiles.Textures.MainMenu.*;

public class Slider {
    private final Rectangle hitbox;
    private final Consumer<Boolean> onSwitch;
    private boolean isEnabled;

    public Slider(Rectangle hitbox, boolean defaultState, Consumer<Boolean> onSwitch) {
        isEnabled = defaultState;
        this.hitbox = hitbox;
        this.onSwitch = onSwitch;
    }

    public void draw(SpriteBatch batch, float mouseX, float mouseY, boolean clicked) {
        boolean lit = hitbox.contains(mouseX, mouseY);
        if (lit && isEnabled) {
            draw(batch, SLIDER_YES_LIT);
        } else if (lit && !isEnabled) {
            draw(batch, SLIDER_NO_LIT);
        } else if (!lit && isEnabled) {
            draw(batch, SLIDER_YES_UNLIT);
        } else {
            draw(batch, SLIDER_NO_UNLIT);
        }
        if (clicked && lit) {
            isEnabled = !isEnabled;
            onSwitch.accept(isEnabled);
        }
    }

    private void draw(SpriteBatch batch, TextureRegion texture) {
        batch.draw(texture, hitbox.x, hitbox.y);
    }
}
