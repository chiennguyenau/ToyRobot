package com.cnn.model;

public interface ToyRobot {
    //Place robot on table base on provided Position and Direction
    void place(Position position, Direction direction);

    //Move robot
    void move();

    //Rotate left
    void left();

    //Rotate right
    void right();

    //Get current position
    Position getPosition();

    //Get next position base on current direction
    Position nextPosition();

    //Get current direction
    Direction getDirection();
}
