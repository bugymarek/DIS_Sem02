/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import AirCarCore.AirCarCore;
import static Constants.Constants.SimulationTime;
import Core.Command;
import java.util.LinkedList;

/**
 *
 * @author Bugy
 */
public class Tests {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        mySimulationCoreTest();
    }   
    
    public static void mySimulationCoreTest(){
        AirCarCore msc = new AirCarCore(5,20, true);
        Command c = new Command() {
            @Override
             public void run() {
                 //double current = msc.getCurrentTime();
                 //System.out.println(msc.getCustomersString());
                     System.out.println(msc.getSumAllWaitingTimes()/msc.getCurrentExperiment());
                             
            }
        };
        msc.setCommand(c);      
        msc.doReprications(30, SimulationTime);
    }
    
}
