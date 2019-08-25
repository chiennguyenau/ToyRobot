package com.cnn.model;

public class ToyRobotImpl implements ToyRobot {
    private Position position;
    private Direction direction;

    public ToyRobotImpl() {
    }

    @Override
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void place(Position position, Direction direction) {
        if ((position != null) && (direction != null)) {
            this.setPosition(position);
            this.setDirection(direction);
        }
    }

    @Override
    public void move() {
        this.setPosition(this.nextPosition());
    }

    //Rotate left 90 degree
    @Override
    public void left() {
        if ((position != null) && (direction != null)) {
            switch (this.direction) {
                case NORTH: {
                    this.direction = Direction.WEST;
                    break;
                }
                case WEST: {
                    this.direction = Direction.SOUTH;
                    break;
                }
                case SOUTH: {
                    this.direction = Direction.EAST;
                    break;
                }
                case EAST: {
                    this.direction = Direction.NORTH;
                    break;
                }
            }
        }
    }

    //Rotate right 90 degree
    @Override
    public void right() {
        if ((position != null) && (direction != null)) {
            switch (this.direction) {
                case NORTH: {
                    this.direction = Direction.EAST;
                    break;
                }
                case EAST: {
                    this.direction = Direction.SOUTH;
                    break;
                }
                case SOUTH: {
                    this.direction = Direction.WEST;
                    break;
                }
                case WEST: {
                    this.direction = Direction.NORTH;
                    break;
                }
            }
        }
    }

    @Override
    public Position nextPosition() {
        Position newPosition = null;
        if ((position != null) && (direction != null)) {
            int currentX = this.position.getX();
            int currentY = this.position.getY();
            switch (direction) {
                case NORTH: {
                    newPosition = new Position(currentX, ++currentY);
                    break;
                }
                case SOUTH: {
                    newPosition = new Position(currentX, --currentY);
                    break;
                }
                case EAST: {
                    newPosition = new Position(++currentX, currentY);
                    break;
                }
                case WEST: {
                    newPosition = new Position(--currentX, currentY);
                    break;
                }
            }
        }
        return newPosition;
    }
}
