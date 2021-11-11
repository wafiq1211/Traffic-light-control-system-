/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testing2;

import trafficlightcontrol.*;

/**
 *
 * @author wafiq
 */
public class ThreadEToW extends Thread  {
    TrafficLight trafficLight = new TrafficLight();
    
    public ThreadEToW(TrafficLight tl) {
        trafficLight = tl;
    }
    
    public void run() {
        try {
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
