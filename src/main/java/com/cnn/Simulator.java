package com.cnn;

import com.cnn.common.Constant;
import com.cnn.model.Direction;
import com.cnn.model.Position;
import com.cnn.model.TableTop;
import com.cnn.model.ToyRobot;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Simulator {
    private final Pattern pattern = Pattern.compile("^PLACE(\\s)(\\d+)(,)(\\d+)(,)(EAST|WEST|NORTH|SOUTH)$");

    private TableTop tableTop;
    private ToyRobot toyRobot;

    public Simulator(TableTop tableTop, ToyRobot toyRobot) {
        this.tableTop = tableTop;
        this.toyRobot = toyRobot;
    }

    protected void placeRobot(Position position, Direction direction) {
        if ((position != null) && (direction != null) && (tableTop.isValidPosition(position))) {
            this.toyRobot.place(position, direction);
        }
    }

    protected void moveRobot(@NotNull ToyRobot toyRobot) {
        if ((toyRobot.getPosition() != null) && tableTop.isValidPosition(toyRobot.nextPosition())) {
            toyRobot.move();
        }
    }

    protected void turnRobotLeft(@NotNull ToyRobot toyRobot) {
        if (toyRobot.getDirection() != null) {
            toyRobot.left();
        }
    }

    protected void turnRobotRight(@NotNull ToyRobot toyRobot) {
        if (toyRobot.getDirection() != null) {
            toyRobot.right();
        }
    }

    public String report(@NotNull ToyRobot toyRobot) {
        if ((toyRobot.getPosition() != null) && (toyRobot.getDirection() != null))
            return String.join(
                    ",",
                    Integer.toString(toyRobot.getPosition().getX()),
                    Integer.toString(toyRobot.getPosition().getY()),
                    toyRobot.getDirection().toString());
        return Constant.NO_ROBOT_MSG;
    }

    public String execute(String inputCommand) {
        String output = "";
        Position inputPosition = null;
        Direction inputDirection = null;

        if (inputCommand.startsWith(Constant.PLACE)) {
            Matcher matcher = pattern.matcher(inputCommand);
            if (matcher.find()) {
                inputPosition = new Position(Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(4)));
                inputDirection = Direction.valueOf(matcher.group(6));
                inputCommand = Constant.PLACE;
            } else inputCommand = Constant.INVALID_COMMAND;
        }

        switch (inputCommand) {
            case Constant.PLACE: {
                this.placeRobot(inputPosition, inputDirection);
                break;
            }
            case Constant.MOVE: {
                this.moveRobot(toyRobot);
                break;
            }
            case Constant.LEFT: {
                this.turnRobotLeft(toyRobot);
                break;
            }
            case Constant.RIGHT: {
                this.turnRobotRight(toyRobot);
                break;
            }
            case Constant.REPORT: {
                output = this.report(toyRobot);
                break;
            }
            case Constant.EXIT: {
                output = Constant.EXIT;
                break;
            }
            default: {
                output = Constant.INVALID_COMMAND;
                break;
            }
        }
        return output;
    }
}
