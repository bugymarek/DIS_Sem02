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
public class DepartureMiniBusAirCar extends AirCarEvent{

    public DepartureMiniBusAirCar(AirCarCore core, double startTime, MiniBus minibus) {
        super(core, startTime, minibus);
    }

    @Override
    public void execute() {
      getCore().plainEvent(new ArrivalMiniBusT1(getCore(), LengtRentalToT1, getMiniBus()));
    }
    
}
