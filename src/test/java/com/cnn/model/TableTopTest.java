package com.cnn.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TableTopTest {

    private TableTop tableTop;

    @Before
    public void setUp() {
        tableTop = new TableTopImpl();
    }

    @Test
    public void testWithPositionInsideTable() {
        for (int i = 0; i < tableTop.getWidth(); i++) {
            for (int j = 0; j < tableTop.getHeight(); j++) {
                Assert.assertTrue(tableTop.isValidPosition(new Position(i, j)));
            }
        }
    }

    @Test
    public void testWithPositionOutsideTable() {
        Assert.assertFalse(tableTop.isValidPosition(new Position(-1, 1)));
        Assert.assertFalse(tableTop.isValidPosition(new Position(6, 1)));
        Assert.assertFalse(tableTop.isValidPosition(new Position(1, -1)));
        Assert.assertFalse(tableTop.isValidPosition(new Position(1, 6)));
    }

    @After
    public void tearDown() {
        tableTop = null;
    }
}
