package game.tiles.controllers;

public interface Controller {

    boolean getLeft();

    boolean getRight();

    boolean getUp();

    boolean getDown();

    boolean getSprint();

    boolean updateTile();

    boolean useItem();

    boolean getDrop();

    boolean zoomIn();

    boolean zoomOut();

}
