/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing;

import trafficlightcontrol.*;

/**
 *
 * @author wafiq
 */
public class Main {
    //this will be the main class, setup thread and run start only I guess.
    public static void main(String[] args) {
        TrafficLight traffic = new TrafficLight();
        ThreadNToS NtoS = new ThreadNToS (traffic);
        ThreadSToN StoN = new ThreadSToN (traffic);
        traffic.insertCars();
        StoN.start();
        NtoS.start();
    }
}
