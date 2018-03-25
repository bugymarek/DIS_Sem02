/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AirCarEvents;

import AirCarCore.AirCarCore;
import AirCarCore.AirCarEvent;
import AirCarCore.MiniBus;
import static Constants.Constants.*;
/**
 *
 * @author Bugy
 */
public class ArrivalMiniBusAirCar extends AirCarEvent{

    public ArrivalMiniBusAirCar(AirCarCore core, double startTime, MiniBus minibus) {
        super(core, startTime, minibus);
    }

    @Override
    public void execute() {
//        System.out.print("Minibus: " + getMiniBus().getID()+ "| Prichod na Rental v cese: " + (getCore().getCurrentTime()));
//        System.out.println(" Pasažieri: " + getMiniBus().getCustomersInBus()+ " pocet: " + getMiniBus().getSize());
//              
        if(getMiniBus().isEmpty()){
            getCore().plainEvent(new DepartureMiniBusAirCar(getCore(), Now, getMiniBus())); 
        } else {
            getCore().plainEvent(new StartGetOutOfBus(getCore(), Now, getMiniBus()));
        }
    }
    
}
