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
public class StartBoardingT2 extends AirCarEvent {

    public StartBoardingT2(AirCarCore core, double startTime, MiniBus minibus, Customer customer) {
        super(core, startTime, minibus, customer);
    }

    @Override
    public void execute() {
            double boardingTime = getCore().getRndBoardToBus().next();
            getCore().plainEvent(new EndBoardingT2(getCore(), boardingTime, getMiniBus(), getCustomer()));
    }

}
