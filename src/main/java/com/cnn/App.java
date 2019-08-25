package com.cnn;

import com.cnn.common.Constant;
import com.cnn.model.TableTop;
import com.cnn.model.TableTopImpl;
import com.cnn.model.ToyRobot;
import com.cnn.model.ToyRobotImpl;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        TableTop tableTop = new TableTopImpl(5, 5);
        ToyRobot toyRobot = new ToyRobotImpl();
        Simulator simulator = new Simulator(tableTop, toyRobot);

        System.out.println("Toy Robot Simulator");
        System.out.println("Enter a command, Valid commands are:");
        System.out.println("\'PLACE X,Y,NORTH|SOUTH|EAST|WEST\', MOVE, LEFT, RIGHT, REPORT or EXIT");

        Scanner scanner = new Scanner(System.in);
        String inputCommand;
        do {
            inputCommand = scanner.nextLine().toUpperCase();
            String output = simulator.execute(inputCommand);
            if (output.equals(Constant.EXIT)) break;
            else if (output.length() > 0) System.out.println(output);
        }
        while (true);
    }
}
