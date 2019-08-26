package com.cnn.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ToyRobotTest {

    private ToyRobotImpl toyRobot;

    @Before
    public void setUp() {
        toyRobot = new ToyRobotImpl();
    }

    @Test
    public void testPlaceWithNullPosition() {
        toyRobot.place(null, Direction.NORTH);
        Assert.assertNull(toyRobot.getPosition());
    }

    @Test
    public void testPlaceWithNullDirection() {
        toyRobot.place(new Position(2, 4), null);
        Assert.assertNull(toyRobot.getDirection());
    }

    @Test
    public void testPlace() {
        toyRobot.place(new Position(2, 4), Direction.NORTH);
        Assert.assertEquals(2, toyRobot.getPosition().getX());
        Assert.assertEquals(4, toyRobot.getPosition().getY());
        Assert.assertEquals(Direction.NORTH, toyRobot.getDirection());
    }

    @Test
    public void testLeftWithNullPosition() {
        toyRobot.place(null, Direction.EAST);
        toyRobot.left();
        Assert.assertNull(toyRobot.getDirection());
    }

    @Test
    public void testLeftWithNullDirection() {
        toyRobot.place(new Position(2, 3), null);
        toyRobot.left();
        Assert.assertNull(toyRobot.getDirection());
    }

    @Test
    public void testLeftWithFourDirection() {
        toyRobot.place(new Position(1, 1), Direction.EAST);
        toyRobot.left();
        Assert.assertEquals(Direction.NORTH, toyRobot.getDirection());
        toyRobot.left();
        Assert.assertEquals(Direction.WEST, toyRobot.getDirection());
        toyRobot.left();
        Assert.assertEquals(Direction.SOUTH, toyRobot.getDirection());
        toyRobot.left();
        Assert.assertEquals(Direction.EAST, toyRobot.getDirection());
    }

    @Test
    public void testRightWithNullPosition() {
        toyRobot.place(null, Direction.EAST);
        toyRobot.right();
        Assert.assertNull(toyRobot.getDirection());
    }

    @Test
    public void testRightWithNullDirection() {
        toyRobot.place(new Position(2, 3), null);
        toyRobot.right();
        Assert.assertNull(toyRobot.getDirection());
    }

    @Test
    public void testRightWithFourDirection() {
        toyRobot.place(new Position(1, 1), Direction.EAST);
        toyRobot.right();
        Assert.assertEquals(Direction.SOUTH, toyRobot.getDirection());
        toyRobot.right();
        Assert.assertEquals(Direction.WEST, toyRobot.getDirection());
        toyRobot.right();
        Assert.assertEquals(Direction.NORTH, toyRobot.getDirection());
        toyRobot.right();
        Assert.assertEquals(Direction.EAST, toyRobot.getDirection());
    }

    @Test
    public void testNextPositionWithNullPosition() {
        toyRobot.place(null, Direction.EAST);
        Assert.assertNull(toyRobot.nextPosition());
    }

    @Test
    public void testNextPositionWithNullDirection() {
        toyRobot.place(new Position(2, 3), null);
        Assert.assertNull(toyRobot.nextPosition());
    }

    @Test
    public void testNextPositionWithFourDirection() {
        Position newPosition;

        toyRobot.place(new Position(2, 3), Direction.EAST);
        newPosition = toyRobot.nextPosition();
        Assert.assertEquals(3, newPosition.getX());
        Assert.assertEquals(3, newPosition.getY());

        toyRobot.place(new Position(2, 3), Direction.WEST);
        newPosition = toyRobot.nextPosition();
        Assert.assertEquals(1, newPosition.getX());
        Assert.assertEquals(3, newPosition.getY());

        toyRobot.place(new Position(2, 3), Direction.NORTH);
        newPosition = toyRobot.nextPosition();
        Assert.assertEquals(2, newPosition.getX());
        Assert.assertEquals(4, newPosition.getY());

        toyRobot.place(new Position(2, 3), Direction.SOUTH);
        newPosition = toyRobot.nextPosition();
        Assert.assertEquals(2, newPosition.getX());
        Assert.assertEquals(2, newPosition.getY());
    }

    @After
    public void tearDown() {
        toyRobot = null;
    }
}
