package com.cnn.integration;

import com.cnn.Simulator;
import com.cnn.common.Constant;
import com.cnn.model.TableTop;
import com.cnn.model.TableTopImpl;
import com.cnn.model.ToyRobot;
import com.cnn.model.ToyRobotImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestPlacingRobot {

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
    //Robot is not on table yet, place robot with invalid position and other commands should be ignored
    public void testCommandAreIgnoredWhenRobotNotOnTableYet() {
        simulator.execute("PLACE 6,5,NORTH");
        simulator.execute(Constant.MOVE);
        simulator.execute(Constant.LEFT);
        simulator.execute(Constant.RIGHT);
        Assert.assertEquals(Constant.NO_ROBOT_MSG, simulator.execute(Constant.REPORT));
    }

    @Test
    //Robot is not on table yet, place robot with valid position successfully
    public void testPlaceRobotSuccessWhenRobotNotOnTableYet() {
        simulator.execute("PLACE 5,5,WEST");
        Assert.assertEquals("5,5,WEST", simulator.execute(Constant.REPORT));
    }

    @Test
    //Robot is on table, continue place with invalid position
    public void testPlaceWithInvalidPositionWhenRobotOnTable() {
        simulator.execute("PLACE 0,0,EAST");
        Assert.assertEquals("0,0,EAST", simulator.execute(Constant.REPORT));
        simulator.execute("PLACE 7,7,NORTH");
        simulator.execute(Constant.MOVE);
        simulator.execute(Constant.LEFT);
        simulator.execute(Constant.RIGHT);
        Assert.assertEquals("1,0,EAST", simulator.execute(Constant.REPORT));
    }

    @Test
    //Robot is on table, continue place with valid position
    public void testPlaceWithValidPositionWhenRobotOnTable() {
        simulator.execute("PLACE 1,1,EAST");
        Assert.assertEquals("1,1,EAST", simulator.execute(Constant.REPORT));
        simulator.execute("PLACE 3,3,SOUTH");
        Assert.assertEquals("3,3,SOUTH", simulator.execute(Constant.REPORT));
    }
}
