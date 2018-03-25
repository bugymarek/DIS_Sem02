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
public class ArrivalMiniBusT2 extends AirCarEvent{

    public ArrivalMiniBusT2(AirCarCore core, double startTime, MiniBus minibus) {
        super(core, startTime, minibus);
    }

    @Override
    public void execute() {
       if(getCore().isEmptyCustomersQueueT2() || !getMiniBus().isPlaceInBus()){
            getCore().plainEvent(new ArrivalAirCar(getCore(), LengtT2ToRental, getMiniBus()));
        } else {
            getCore().plainEvent(new StartBoardingT2(getCore(), Now, getMiniBus()));
        }
    }
    
}
