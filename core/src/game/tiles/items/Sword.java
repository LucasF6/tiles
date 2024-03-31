package game.tiles.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import game.tiles.entities.Attackable;
import game.tiles.entities.Effect;
import game.tiles.entities.Entity;

import static game.tiles.Textures.Effects.BOOM;
import static game.tiles.Textures.Items.SWORD;

public class Sword extends Item {
    public Sword() {
        super("sword", false);
    }

    public boolean use(float x, float y) {
        Array<Entity> entities = Entity.getEntities();
        for (int i = 0; i < entities.size; i++) {
            if (entities.get(i) instanceof Attackable && entities.get(i).overlaps(x, y)) {
                ((Attackable) entities.get(i)).getHit(1.0f);
                new Effect(x, y, 0.5f, 0.5f, BOOM);
            }
        }
        return false;
    }

    public TextureRegion getTexture() {
        return SWORD;
    }

}
