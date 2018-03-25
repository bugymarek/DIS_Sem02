/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AirCarCore;

import static Constants.Constants.*;

/**
 *
 * @author Bugy
 */
public class EndBoardingT1 extends AirCarEvent{

    public EndBoardingT1(AirCarCore core, double startTime, MiniBus minibus) {
        super(core, startTime, minibus);
    }

    @Override
    public void execute() {
        getMiniBus().addCustomerToBus(getCore().getCustomerFromQueueT1());
        getCore().plainEvent(new StartBoardingT1(getCore(), Now, getMiniBus()));
    }
    
}
