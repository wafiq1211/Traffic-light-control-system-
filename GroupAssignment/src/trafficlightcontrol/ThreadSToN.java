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
public class ThreadSToN extends Thread  {
    TrafficLight trafficLight = new TrafficLight();
    
    public ThreadSToN(TrafficLight tl) {
        trafficLight = tl;
    }
    
    public void run() {
        try {
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
