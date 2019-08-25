package com.cnn.unit;

import com.cnn.Simulator;
import com.cnn.common.Constant;
import com.cnn.model.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SimulatorTest {

    private TableTop tableTop;
    private ToyRobot toyRobot;
    private Simulator simulator;

    @Before
    public void setUp() {
        tableTop = new TableTopImpl();
        toyRobot = new ToyRobotImpl();
        simulator = new Simulator(tableTop, toyRobot);
    }

    @Test
    public void testPlaceRobotWithValidPosition() {
    }

    @Test
    public void testPlaceRobotWithInvalidPosition() {
    }

    @Test
    public void testReportWhenPositionIsNull() {
        toyRobot.place(null, Direction.NORTH);
        Assert.assertEquals(Constant.NO_ROBOT_MSG, simulator.report(toyRobot));
    }

    @Test
    public void testReportWhenDirectionIsNull() {
        toyRobot.place(new Position(1, 2), null);
        Assert.assertEquals(Constant.NO_ROBOT_MSG, simulator.report(toyRobot));
    }

    @Test
    public void testReportWhenPositionAndDirectionNotNull() {
        toyRobot.place(new Position(2, 3), Direction.EAST);
        Assert.assertEquals("2,3,EAST", simulator.report(toyRobot));
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
        tableTop = null;
        toyRobot = null;
        simulator = null;
    }
}
