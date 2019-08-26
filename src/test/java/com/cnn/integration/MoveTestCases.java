package com.cnn.integration;

import com.cnn.Simulator;
import com.cnn.common.Constant;
import com.cnn.model.TableTop;
import com.cnn.model.TableTopImpl;
import com.cnn.model.ToyRobot;
import com.cnn.model.ToyRobotImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MoveTestCases {

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
    public void testMoveWhenPositionSmallerThanTableDimension() {
        simulator.execute("PLACE 3,2,NORTH");
        simulator.execute(Constant.MOVE);
        Assert.assertEquals("3,3,NORTH", simulator.execute(Constant.REPORT));
    }

    @Test
    public void testMoveAtOneConnerWithFourDirection() {
        simulator.execute("PLACE 5,5,NORTH");
        simulator.execute(Constant.MOVE);
        Assert.assertEquals("5,5,NORTH", simulator.execute(Constant.REPORT));

        simulator.execute("PLACE 5,5,WEST");
        simulator.execute(Constant.MOVE);
        Assert.assertEquals("4,5,WEST", simulator.execute(Constant.REPORT));

        simulator.execute("PLACE 5,5,SOUTH");
        simulator.execute(Constant.MOVE);
        Assert.assertEquals("5,4,SOUTH", simulator.execute(Constant.REPORT));

        simulator.execute("PLACE 5,5,EAST");
        simulator.execute(Constant.MOVE);
        Assert.assertEquals("5,5,EAST", simulator.execute(Constant.REPORT));
    }

    @Test
    public void testMoveOutboundToEast() {
        simulator.execute("PLACE 0,0,EAST");
        for (int i = 0; i < tableTop.getWidth() + 3; i++) {
            simulator.execute(Constant.MOVE);
        }
        Assert.assertEquals("5,0,EAST", simulator.execute(Constant.REPORT));
    }

    @Test
    public void testMoveOutboundToWest() {
        simulator.execute("PLACE 5,0,WEST");
        for (int i = 0; i < tableTop.getWidth() + 3; i++) {
            simulator.execute(Constant.MOVE);
        }
        Assert.assertEquals("0,0,WEST", simulator.execute(Constant.REPORT));
    }

    @Test
    public void testMoveOutboundToSouth() {
        simulator.execute("PLACE 0,5,SOUTH");
        for (int i = 0; i < tableTop.getWidth() + 3; i++) {
            simulator.execute(Constant.MOVE);
        }
        Assert.assertEquals("0,0,SOUTH", simulator.execute(Constant.REPORT));
    }

    @Test
    public void testMoveOutboundToNorth() {
        simulator.execute("PLACE 0,0,NORTH");
        for (int i = 0; i < tableTop.getWidth() + 3; i++) {
            simulator.execute(Constant.MOVE);
        }
        Assert.assertEquals("0,5,NORTH", simulator.execute(Constant.REPORT));
    }

    @After
    public void tearDown() {
        tableTop = null;
        toyRobot = null;
        simulator = null;
    }

}
