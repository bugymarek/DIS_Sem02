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
public class EndBoardingT1 extends AirCarEvent{

    public EndBoardingT1(AirCarCore core, double startTime, MiniBus minibus, Customer customer) {
        super(core, startTime, minibus, customer);
    }

    @Override
    public void execute() {
        getMiniBus().addCustomerToBus(getCustomer());
        if(getMiniBus().isPlaceInBus() && !getCore().isEmptyCustomersQueueT1()){
            getCore().plainEvent(new StartBoardingT1(getCore(), Now, getMiniBus(), getCore().getCustomerFromQueueT1()));
        } else {
            getCore().plainEvent(new ArrivalMiniBusT2(getCore(), LengtT1ToT2, getMiniBus()));
        }
    }
    
}
