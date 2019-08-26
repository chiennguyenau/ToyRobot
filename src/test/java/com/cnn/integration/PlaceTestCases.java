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

public class PlaceTestCases {

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
    public void testCommandAreIgnoredUntilValidPlace() {
        //Commands are ignored before a valid place
        simulator.execute("PLACE 6,6,EAST");
        simulator.execute(Constant.MOVE);
        simulator.execute(Constant.LEFT);
        simulator.execute(Constant.RIGHT);
        Assert.assertEquals(Constant.NO_ROBOT_MSG, simulator.execute(Constant.REPORT));
        //Valid place
        simulator.execute("PLACE 0,0,WEST");
        Assert.assertEquals("0,0,WEST", simulator.execute(Constant.REPORT));
    }

    @Test
    public void testMultiplePlaceWithInvalidPlaceCommand() {
        simulator.execute("PLACE 0,0,EAST");
        simulator.execute("PLACE 7,7,NORTH");
        Assert.assertEquals("0,0,EAST", simulator.execute(Constant.REPORT));
    }

    @Test
    public void testMultiplePlaceCommand() {
        simulator.execute("PLACE 1,1,EAST");
        simulator.execute("PLACE 3,3,SOUTH");
        Assert.assertEquals("3,3,SOUTH", simulator.execute(Constant.REPORT));
    }

    @Test
    public void testPlaceAtFourConnerWithTheSameDirection() {
        simulator.execute("PLACE 0,0,SOUTH");
        Assert.assertEquals("0,0,SOUTH", simulator.execute(Constant.REPORT));

        simulator.execute("PLACE 0,5,SOUTH");
        Assert.assertEquals("0,5,SOUTH", simulator.execute(Constant.REPORT));

        simulator.execute("PLACE 5,0,SOUTH");
        Assert.assertEquals("5,0,SOUTH", simulator.execute(Constant.REPORT));

        simulator.execute("PLACE 5,5,SOUTH");
        Assert.assertEquals("5,5,SOUTH", simulator.execute(Constant.REPORT));
    }

    @Test
    public void testPlaceAtOnePositionWithFourDirection() {
        simulator.execute("PLACE 5,5,EAST");
        Assert.assertEquals("5,5,EAST", simulator.execute(Constant.REPORT));

        simulator.execute("PLACE 5,5,WEST");
        Assert.assertEquals("5,5,WEST", simulator.execute(Constant.REPORT));

        simulator.execute("PLACE 5,5,SOUTH");
        Assert.assertEquals("5,5,SOUTH", simulator.execute(Constant.REPORT));

        simulator.execute("PLACE 5,5,NORTH");
        Assert.assertEquals("5,5,NORTH", simulator.execute(Constant.REPORT));
    }

    @After
    public void tearDown() {
        tableTop = null;
        toyRobot = null;
        simulator = null;
    }
}
