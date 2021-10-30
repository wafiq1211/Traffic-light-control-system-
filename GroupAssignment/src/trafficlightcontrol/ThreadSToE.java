/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficlightcontrol;

/**
 *
 * @author wafiq
 */
public class ThreadSToE extends Thread  {
    TrafficLight trafficLight = new TrafficLight();
    
    public ThreadSToE(TrafficLight tl) {
        trafficLight = tl;
    }
    
    public void run() {
        try {
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
