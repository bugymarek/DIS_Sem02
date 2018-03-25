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
public class StartGetOutOfBus  extends AirCarEvent{

    public StartGetOutOfBus(AirCarCore core, double startTime, MiniBus minibus) {
        super(core, startTime, minibus);
    }

    @Override
    public void execute() {
        if(!getMiniBus().isEmpty()){
            double getOutOfBusTime = getCore().getRndDropFromBus().next();
            getCore().plainEvent(new EndGetOutOfBus(getCore(), getOutOfBusTime, getMiniBus()));
        } else {
            getCore().plainEvent(new DepartureMiniBusAirCar(getCore(), Now, getMiniBus()));
        }    
    }
    
}
