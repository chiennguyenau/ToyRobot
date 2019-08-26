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

public class RightTestCases {
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
    public void testRightAfterValidPlaceCommand() {
        simulator.execute("PLACE 2,2,NORTH");
        simulator.execute(Constant.RIGHT);
        Assert.assertEquals("2,2,EAST", simulator.execute(Constant.REPORT));
    }

    @Test
    public void testMultipleLeftCommand() {
        simulator.execute("PLACE 5,5,NORTH");
        for (int i = 0; i < 4; i++) {
            simulator.execute(Constant.RIGHT);
        }
        Assert.assertEquals("5,5,NORTH", simulator.execute(Constant.REPORT));
    }

    @After
    public void tearDown() {
        tableTop = null;
        toyRobot = null;
        simulator = null;
    }
}
