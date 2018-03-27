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
public class ArrivalMiniBusT2 extends AirCarEvent {

    public ArrivalMiniBusT2(AirCarCore core, double startTime, MiniBus minibus) {
        super(core, startTime, minibus);
    }

    @Override
    public void execute() {
//       System.out.print("Minibus: " + getMiniBus().getID()+ "| Prichod na T2 v cese: " + (getCore().getCurrentTime()));
//       String str = getMiniBus().getCustomersInBus();
//       System.out.println(" Pasažieri: " + str + " pocet: " + getMiniBus().getSize());
//       
        if (getCore().isEmptyCustomersQueueT2() || !getMiniBus().isPlaceInBus()) {
            getMiniBus().setPosition("Cestujem z T2 do Pozičovne");
            getCore().plainEvent(new ArrivalMiniBusAirCar(getCore(), LengtT2ToRental, getMiniBus()));
        } else {
            getCore().plainEvent(new StartBoardingT2(getCore(), Now, getMiniBus(), getCore().getCustomerFromQueueT2()));
        }
    }

}
