package game.tiles.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Button {
    private final Rectangle hitbox;
    private final TextureRegion lit;
    private final TextureRegion unlit;
    private final Runnable action;

    public Button(Rectangle hitbox, TextureRegion lit, TextureRegion unlit, Runnable action) {
        this.hitbox = hitbox;
        this.lit = lit;
        this.unlit = unlit;
        this.action = action;
    }

    public void draw(SpriteBatch batch, float mouseX, float mouseY, boolean clicked) {
        if (hitbox.contains(mouseX, mouseY)) {
            batch.draw(lit, hitbox.x, hitbox.y);
            if (clicked) {
                action.run();
            }
        } else {
            batch.draw(unlit, hitbox.x, hitbox.y);
        }
    }
}
