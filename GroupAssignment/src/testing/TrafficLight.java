/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author wafiq
 */
public class TrafficLight {
    Random rand = new Random(); //insert random number of cars
    int i, carsToAdd, counter;
    int carsCount = 1;
    ArrayList<Integer> carNtoS = new ArrayList<Integer>();
    ArrayList<Integer> carStoN = new ArrayList<Integer>();
    boolean NtoSLock = true;
    
    public synchronized void NtoS() throws InterruptedException {
        while (!NtoSLock) {
            System.out.println("Traffic light North to South is red.");
            wait();
        }
        //turn give to thread 1
        ForNtoS();
        insertCars();
        NtoSLock = false;
        notify();
    }
    
    public synchronized void StoN() throws InterruptedException {
        while (NtoSLock) {
            System.out.println("Traffic light South to North is red.");
            wait();
        }
        //turn give to thread 1
        ForStoN();
        insertCars();
        NtoSLock = true;
        notify();
    }
    
    //method for inserting cars when the traffic signal is red
    public void insertCars() { 
        carsToAdd = rand.nextInt(3);
        for (i = 1; i <= 3; i++) {
            System.out.println("Car " + carsCount + " arrived from North, heading South.");
            carNtoS.add(carsCount);
            carsCount++;
        }
        
        carsToAdd = rand.nextInt(3);
        for (i = 1; i <= 3; i++) {
            System.out.println("Car " + carsCount + " arrived from South, heading North.");
            carStoN.add(carsCount);
            carsCount++;
        }
        
        System.out.println();
    }
    
    public void ForNtoS() throws InterruptedException {
        System.out.println("Traffic light North to South turns green.");
        
        if (carNtoS.size() < 10) {
            System.out.println("Less than 10 cars datected, traffic light North to South will stay green for 90 seconds.");
            
            for (i = 0; i < 6; i++) {
                System.out.println("Car " + carNtoS.get(0) + " goes straight to South from North.");
                Thread.sleep(1000);
                carNtoS.remove(0);
                
                if (carNtoS.size() == 0) {
                    break;
                }
            }
            
            if (carNtoS.size() == 0 && i < 5) {
                System.out.println("No more cars detected for 10 seconds. Traffic light North to South turning yellow for 3 seconds.");
                System.out.print("3...");
                Thread.sleep(1000);
                System.out.print("2...");
                Thread.sleep(1000);
                System.out.print("1...");
                Thread.sleep(1000);
                System.out.println("0...");
                System.out.println("No more cars detected. Traffic light North to South will turn from yellow to red.");
            } else {
                System.out.println("Traffic light North to South turns red");
            }
        } else {
            System.out.println("More than 10 cars datected, therefore traffic light North to South will stay green for 120 seconds.");
            
            for (i = 0; i < 11; i++) {
                System.out.println("Car " + carNtoS.get(0) + " goes straight to South from North.");
                Thread.sleep(1000);
                carNtoS.remove(0);
                
                if (carNtoS.size() == 0) {
                    break;
                }
            }
            
            if (carNtoS.size() == 0 && i < 10) {
                System.out.println("No more cars detected for 10 seconds. Traffic light North to South turning yellow for 3 seconds.");
                System.out.print("3...");
                Thread.sleep(1000);
                System.out.print("2...");
                Thread.sleep(1000);
                System.out.print("1...");
                Thread.sleep(1000);
                System.out.println("0...");
                System.out.println("No more cars detected. Traffic light North to South will turn from yellow to red.");
            } else {
                System.out.println("Traffic light North to South turns red");
            }
        }
        System.out.println();
    }
    
    public void ForStoN() throws InterruptedException {
        System.out.println("Traffic light South to North turns green.");
        
        if (carNtoS.size() < 10) {
            System.out.println("Less than 10 cars datected, traffic light South to North will stay green for 90 seconds.");
            
            for (i = 0; i < 6; i++) {
                System.out.println("Car " + carNtoS.get(0) + " goes straight to North from South.");
                Thread.sleep(1000);
                carNtoS.remove(0);
                
                if (carNtoS.size() == 0) {
                    break;
                }
            }
            
            if (carNtoS.size() == 0 && i < 5) {
                System.out.println("No more cars detected for 10 seconds. Traffic light South to North turning yellow for 3 seconds.");
                System.out.print("3...");
                Thread.sleep(1000);
                System.out.print("2...");
                Thread.sleep(1000);
                System.out.print("1...");
                Thread.sleep(1000);
                System.out.println("0...");
                System.out.println("No more cars detected. Traffic light South to North will turn from yellow to red.");
            } else {
                System.out.println("Traffic light South to North turns red");
            }
        } else {
            System.out.println("More than 10 cars datected, therefore traffic light South to North will stay green for 120 seconds.");
            
            for (i = 0; i < 10; i++) {
                System.out.println("Car " + carNtoS.get(0) + " goes straight to South from North.");
                Thread.sleep(1000);
                carNtoS.remove(0);
                
                if (carNtoS.size() == 0) {
                    break;
                }
            }
            
            if (carNtoS.size() == 0 && i < 9) {
                System.out.println("No more cars detected for 10 seconds. Traffic light South to North turning yellow for 3 seconds.");
                System.out.print("3...");
                Thread.sleep(1000);
                System.out.print("2...");
                Thread.sleep(1000);
                System.out.print("1...");
                Thread.sleep(1000);
                System.out.println("0...");
                System.out.println("No more cars detected. Traffic light South to North will turn from yellow to red.");
            } else {
                System.out.println("Traffic light South to North turns red");
            }
        }
        System.out.println();
    }
}
