/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AirCarEvents;

import AirCarCore.AirCarCore;
import AirCarCore.AirCarEvent;
import AirCarCore.Customer;
import AirCarCore.MiniBus;
import static Constants.Constants.*;

/**
 *
 * @author Bugy
 */
public class EndBoardingT2 extends AirCarEvent{

    public EndBoardingT2(AirCarCore core, double startTime, MiniBus minibus, Customer customer) {
        super(core, startTime, minibus, customer);
    }

    @Override
    public void execute() {
        getMiniBus().addCustomerToBus(getCustomer());
        if (getMiniBus().isPlaceInBus() && !getCore().isEmptyCustomersQueueT2()) {
            getCore().plainEvent(new StartBoardingT2(getCore(), Now, getMiniBus(), getCore().getCustomerFromQueueT2()));
        } else {
            getCore().plainEvent(new ArrivalMiniBusAirCar(getCore(), LengtT2ToRental, getMiniBus()));
        }      
    }
    
}
