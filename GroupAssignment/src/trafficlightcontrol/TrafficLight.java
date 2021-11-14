/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficlightcontrol;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author wafiq
 */
public class TrafficLight {

    Random rand = new Random(); //insert random number of cars
    int i, carsToAdd, counter;
    int turn = 10;
    int carsCount = 1;
    ArrayList<Integer> carNtoS = new ArrayList<>();
    ArrayList<Integer> carNtoW = new ArrayList<>();
    ArrayList<Integer> carEtoW = new ArrayList<>();
    ArrayList<Integer> carEtoN = new ArrayList<>();
    ArrayList<Integer> carStoN = new ArrayList<>();
    ArrayList<Integer> carStoE = new ArrayList<>();
    ArrayList<Integer> carWtoE = new ArrayList<>();
    ArrayList<Integer> carWtoS = new ArrayList<>();
    boolean northLock = false;
    boolean eastLock = true;
    boolean southLock = true;
    boolean westLock = true;

    public synchronized void processTraffic(String directionFrom) throws InterruptedException {
        while (counter < turn) {
            switch (directionFrom) {
                case "North":
                    while (northLock) {
                        wait();
                    }
                    break;
                case "East":
                    while (eastLock) {
                        wait();
                    }
                    break;
                case "South":
                    while (southLock) {
                        wait();
                    }
                    break;
                case "West":
                    while (westLock) {
                        wait();
                    }
                    break;
                default: break;
            }

            if (counter == turn) {
                System.exit(0);
            }

            switch (directionFrom) {
                case "North":
                    GreenLight("North", "South", "West", carNtoS, carNtoW);
                    break;
                case "East":
                    GreenLight("East", "West", "North", carEtoW, carEtoN);
                    break;
                case "South":
                    GreenLight("South", "North", "East", carStoN, carStoE);
                    break;
                case "West":
                    GreenLight("West", "East", "South", carNtoS, carWtoS);
                    break;
                default: break;
            }

            insertCars();
            flipCondition(directionFrom);
            notifyAll();
        }
    }
    
    public void flipCondition(String directionFrom)  {
        switch (directionFrom) {
            case "North":
                northLock = true;
                eastLock = false;
                break;
            case "East":
                eastLock = true;
                southLock = false;
                break;
            case "South":
                southLock = true;
                westLock = false;
                break;
            case "West":
                westLock = true;
                northLock = false;
                break;
            default: break;
        }
    }
    
    public void addCars(String directionFrom, String directionTo, ArrayList<Integer> carQueue) throws InterruptedException {
        carsToAdd = rand.nextInt(3);
        for (i = 0; i < carsToAdd; i++) {
            System.out.println("Car " + carsCount + " arrived from " + directionFrom + ", heading " + directionTo + ".");
            carQueue.add(carsCount);
            carsCount++;
            Thread.sleep(500);
        }
    }

    //method for inserting cars when the traffic signal is red
    public void insertCars() throws InterruptedException {
        System.out.println("------------------------------------------------------------------------------");
        addCars("North", "South", carNtoS);
        addCars("North", "West", carNtoW);
        addCars("East", "West", carEtoW);
        addCars("East", "North", carEtoN);
        addCars("South", "North", carStoN);
        addCars("South", "East", carStoE);
        addCars("West", "East", carWtoE);
        addCars("West", "South", carWtoS);

        System.out.println();
        System.out.println("Number of cars waiting from North, heading South: " + carNtoS.size());
        System.out.println("Number of cars waiting from North, heading West: " + carNtoW.size());
        System.out.println("Number of cars waiting from East, heading West: " + carEtoW.size());
        System.out.println("Number of cars waiting from East, heading North: " + carEtoN.size());
        System.out.println("Number of cars waiting from South, heading North: " + carStoN.size());
        System.out.println("Number of cars waiting from South, heading East: " + carStoE.size());
        System.out.println("Number of cars waiting from West, heading East: " + carWtoE.size());
        System.out.println("Number of cars waiting from West, heading South: " + carWtoS.size());
        System.out.println();
    }

    public void GreenLight(String directionFrom, String directionTo, String directionRight, ArrayList<Integer> carQueue, ArrayList<Integer> carQueueRight) throws InterruptedException {
        System.out.println("Traffic light " + directionFrom + " to " + directionTo + " turns green.");
        if (carQueue.isEmpty()) {
            Thread.sleep(1000);
            System.out.println("No cars detected for 10 seconds. Traffic light " + directionFrom + " to " + directionTo + " turning yellow for 3 seconds.");
            System.out.print("3...");
            Thread.sleep(1000);
            System.out.print("2...");
            Thread.sleep(1000);
            System.out.print("1...");
            Thread.sleep(1000);
            System.out.println("0...");
            System.out.println("No more cars detected. Traffic light " + directionFrom + " to " + directionTo + " will turn from yellow to red.");
            Thread.sleep(500);
        } else if (carQueue.size() < 10) {
            System.out.println("Less than 10 cars datected, traffic light " + directionFrom + " to " + directionTo + " will stay green for 90 seconds.\n");

            for (i = 1; i < 6; i++) {
                System.out.println("Car " + carQueue.get(0) + " goes to " + directionTo + " from " + directionFrom + ".");
                Thread.sleep(1000);
                carQueue.remove(0);

                if (carQueue.isEmpty()) {
                    break;
                }
            }

            if (carQueue.isEmpty() && i < 5) {
                Thread.sleep(1000);
                System.out.println("No more cars detected for 10 seconds. Traffic light " + directionFrom + " to " + directionTo + " turning yellow for 3 seconds.");
                System.out.print("3...");
                Thread.sleep(1000);
                System.out.print("2...");
                Thread.sleep(1000);
                System.out.print("1...");
                Thread.sleep(1000);
                System.out.println("0...");
                System.out.println("No more cars detected. Traffic light " + directionFrom + " to " + directionTo + " will turn from yellow to red.");
                Thread.sleep(500);
            } else {
                System.out.println("Traffic light " + directionFrom + " to " + directionTo + " turns to yellow then red");
                System.out.println("Number of cars waiting from " + directionFrom + ", heading " + directionTo + ": " + carQueue.size());
                Thread.sleep(500);
            }
        } else {
            System.out.println("10 or more cars datected, therefore traffic light " + directionFrom + " to " + directionTo + " will stay green for 120 seconds.\n");

            for (i = 1; i < 11; i++) {
                System.out.println("Car " + carQueue.get(0) + " goes to " + directionTo + " from " + directionFrom + ".");
                Thread.sleep(1000);
                carQueue.remove(0);

                if (carQueue.isEmpty()) {
                    break;
                }
            }

            if (carQueue.isEmpty() && i < 11) {
                Thread.sleep(1000);
                System.out.println("No more cars detected for 10 seconds. Traffic light " + directionFrom + " to " + directionTo + " turning yellow for 3 seconds.");
                System.out.print("3...");
                Thread.sleep(1000);
                System.out.print("2...");
                Thread.sleep(1000);
                System.out.print("1...");
                Thread.sleep(1000);
                System.out.println("0...");
                System.out.println("No more cars detected. Traffic light " + directionFrom + " to " + directionTo + " will turn from yellow to red.");
                Thread.sleep(500);
            } else {
                System.out.println("Traffic light " + directionFrom + " to " + directionTo + " turns to yellow then red");
                System.out.println("Number of cars waiting from " + directionFrom + ", heading " + directionTo + ": " + carQueue.size());
                Thread.sleep(500);
            }
        }
        System.out.println();
    }
}