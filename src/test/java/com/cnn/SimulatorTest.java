package com.cnn;

import com.cnn.common.Constant;
import com.cnn.model.Direction;
import com.cnn.model.Position;
import com.cnn.model.TableTop;
import com.cnn.model.ToyRobot;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

public class SimulatorTest {

    private TableTop tableTopMock;
    private ToyRobot toyRobotMock;
    private Simulator simulator;

    @Before
    public void setUp() {
        toyRobotMock = Mockito.mock(ToyRobot.class);
        tableTopMock = Mockito.mock(TableTop.class);
        simulator = new Simulator(tableTopMock, toyRobotMock);
    }

    @Test
    public void testPlaceRobotWithNullPosition() {
        Mockito.when(tableTopMock.isValidPosition(Matchers.any(Position.class))).thenReturn(true);
        simulator.placeRobot(null, Direction.EAST);
        Mockito.verify(toyRobotMock, Mockito.never()).place(null, Direction.EAST);
    }

    @Test
    public void testPlaceRobotWithNullDirection() {
        Mockito.when(tableTopMock.isValidPosition(Matchers.any(Position.class))).thenReturn(true);
        simulator.placeRobot(new Position(2, 2), null);
        Mockito.verify(toyRobotMock, Mockito.never()).place(new Position(2, 2), null);
    }

    @Test
    public void testPlaceRobotWithInvalidPosition() {
        Mockito.when(tableTopMock.isValidPosition(Matchers.any(Position.class))).thenReturn(false);
        simulator.placeRobot(new Position(1, 1), Direction.EAST);
        Mockito.verify(toyRobotMock, Mockito.never()).place(new Position(1, 1), Direction.EAST);
    }

    @Test
    public void testPlaceRobotWithValidPosition() {
        Mockito.when(tableTopMock.isValidPosition(Matchers.any(Position.class))).thenReturn(true);
        Position position = new Position(1, 1);
        simulator.placeRobot(position, Direction.EAST);
        Mockito.verify(toyRobotMock, Mockito.times(1)).place(position, Direction.EAST);
    }

    @Test
    public void testMoveRobotWithNullPosition() {
        Mockito.when(toyRobotMock.getPosition()).thenReturn(null);
        Mockito.when(tableTopMock.isValidPosition(Matchers.any(Position.class))).thenReturn(true);
        simulator.moveRobot(toyRobotMock);
        Mockito.verify(toyRobotMock, Mockito.never()).move();
    }

    @Test
    public void testMoveRobotWithNotNullPosition() {
        Mockito.when(toyRobotMock.getPosition()).thenReturn(new Position(1, 1));
        Mockito.when(tableTopMock.isValidPosition(Matchers.any(Position.class))).thenReturn(true);
        simulator.moveRobot(toyRobotMock);
        Mockito.verify(toyRobotMock, Mockito.times(1)).move();
    }

    @Test
    public void testMoveRobotWithInvalidPosition() {
        Mockito.when(toyRobotMock.getPosition()).thenReturn(new Position(1, 1));
        Mockito.when(tableTopMock.isValidPosition(Matchers.any(Position.class))).thenReturn(false);
        simulator.moveRobot(toyRobotMock);
        Mockito.verify(toyRobotMock, Mockito.never()).move();
    }

    @Test
    public void testMoveRobotWithValidPosition() {
        Mockito.when(toyRobotMock.getPosition()).thenReturn(new Position(1, 1));
        Mockito.when(tableTopMock.isValidPosition(Matchers.any(Position.class))).thenReturn(true);
        simulator.moveRobot(toyRobotMock);
        Mockito.verify(toyRobotMock, Mockito.times(1)).move();
    }

    @Test
    public void testTurnRobotLeftWithNullDirection() {
        Mockito.when(toyRobotMock.getDirection()).thenReturn(null);
        simulator.turnRobotLeft(toyRobotMock);
        Mockito.verify(toyRobotMock, Mockito.never()).left();
    }

    @Test
    public void testTurnRobotLeftNotNullDirection() {
        Mockito.when(toyRobotMock.getDirection()).thenReturn(Direction.EAST);
        simulator.turnRobotLeft(toyRobotMock);
        Mockito.verify(toyRobotMock, Mockito.times(1)).left();
    }

    @Test
    public void testTurnRobotRightNullDirection() {
        ToyRobot toyRobotMock = Mockito.mock(ToyRobot.class);
        Mockito.when(toyRobotMock.getDirection()).thenReturn(null);
        simulator.turnRobotRight(toyRobotMock);
        Mockito.verify(toyRobotMock, Mockito.never()).right();
    }

    @Test
    public void testTurnRobotRightNotNullDirection() {
        ToyRobot toyRobotMock = Mockito.mock(ToyRobot.class);
        Mockito.when(toyRobotMock.getDirection()).thenReturn(Direction.EAST);
        simulator.turnRobotRight(toyRobotMock);
        Mockito.verify(toyRobotMock, Mockito.times(1)).right();
    }

    @Test
    public void testReportWhenPositionIsNull() {
        Mockito.when(toyRobotMock.getPosition()).thenReturn(null);
        Assert.assertEquals(Constant.NO_ROBOT_MSG, simulator.report(toyRobotMock));
    }

    @Test
    public void testReportWhenDirectionIsNull() {
        Mockito.when(toyRobotMock.getDirection()).thenReturn(null);
        Assert.assertEquals(Constant.NO_ROBOT_MSG, simulator.report(toyRobotMock));
    }

    @Test
    public void testReportWhenPositionAndDirectionNotNull() {
        Mockito.when(toyRobotMock.getPosition()).thenReturn(new Position(2, 3));
        Mockito.when(toyRobotMock.getDirection()).thenReturn(Direction.EAST);
        Assert.assertEquals("2,3,EAST", simulator.report(toyRobotMock));
    }

    @Test
    public void testExecuteWhenCommandStartWithPlaceButNotMatchPattern() {
        Assert.assertEquals(Constant.INVALID_COMMAND, simulator.execute("place ,3,N"));
    }

    @Test
    public void testExecuteWhenCommandStartWithPlaceAndMatchPattern() {
        Assert.assertEquals("", simulator.execute("PLACE 2,3,NORTH"));
    }

    @Test
    public void testExecuteWithInvalidCommand() {
        Assert.assertEquals(Constant.INVALID_COMMAND, simulator.execute("abc"));
    }

    @Test
    public void testExecuteWithMoveCommand() {
        Assert.assertEquals("", simulator.execute(Constant.MOVE));
    }

    @Test
    public void testExecuteWithLeftCommand() {
        Assert.assertEquals("", simulator.execute(Constant.LEFT));
    }

    @Test
    public void testExecuteWithRightCommand() {
        Assert.assertEquals("", simulator.execute(Constant.RIGHT));
    }

    @Test
    public void testExecuteWithExitCommand() {
        Assert.assertEquals(Constant.EXIT, simulator.execute(Constant.EXIT));
    }

    @After
    public void tearDown() {
        tableTopMock = null;
        toyRobotMock = null;
        simulator = null;
    }
}
