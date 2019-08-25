package com.cnn.model;

public interface TableTop {
    //The origin (0,0) for the South West most conner
    int XROOT = 0;
    int YROOT = 0;

    //Get the width of the table
    int getWidth();

    //Get the height of the table
    int getHeight();

    //Check if a provided position is a valid position on the table
    boolean isValidPosition(Position position);
}
