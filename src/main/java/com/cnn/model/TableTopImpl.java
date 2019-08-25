package com.cnn.model;

public class TableTopImpl implements TableTop {

    private int width;
    private int height;

    public TableTopImpl() {
        width = 5;
        height = 5;
    }

    public TableTopImpl(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public boolean isValidPosition(Position position) {
        return !((position.getX() > this.width) || (position.getX() < XROOT) ||
                (position.getY() > this.height) || (position.getY() < YROOT));
    }
}
