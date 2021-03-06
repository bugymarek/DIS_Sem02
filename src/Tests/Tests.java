/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import AirCarCore.AirCarCore;
import static Constants.Constants.*;
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
       //mySimulationCoreTest();
        
       //testConstants();
    }   
    
    public static void mySimulationCoreTest(){
        AirCarCore msc = new AirCarCore(7,18, true);
        Command c = new Command() {
            @Override
             public void run() {
                 //double current = msc.getCurrentTime();
                 //System.out.println(msc.getCustomersString());
                 System.out.println(msc.getSumAllWaitingTimes()/msc.getCurrentExperiment()/60.0);
                             
            }
        };
        msc.setCommand(c);      
        msc.doReprications(1000, SimulationTime);
    }

    private static void testConstants() {
        System.out.println("LampdaArrivalT1: " + LampdaArrivalT1);
        System.out.println("LampdaArrivalT1: " + LampdaArrivalT2);
        System.out.println("BoardingLowerLimit: " + BoardingLowerLimit);
        System.out.println("BoardingUpperLimit: " + BoardingUpperLimit);
        System.out.println("GetOutOfBusLowerLimit: " + GetOutOfBusLowerLimit);
        System.out.println("GetOutOfBusUpperLimit: " + GetOutOfBusUpperLimit);
        System.out.println("LengtRentalToT1: " + LengtRentalToT1);
        System.out.println("LengtT1ToT2: " + LengtT1ToT2);
        System.out.println("LengtT2ToRental: " + LengtT2ToRental);
        System.out.println("OperatingLowerLimit: " +  OperatingLowerLimit);
        System.out.println("OperatingUpperLimit: " + OperatingUpperLimit);
        System.out.println("SimulationTime: " + SimulationTime);
    }
    
}
