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
public class StartBoardingT1 extends AirCarEvent{

    public StartBoardingT1(AirCarCore core, double startTime, MiniBus minibus) {
        super(core, startTime, minibus);
    }

    @Override
    public void execute() {
        if(getMiniBus().isPlaceInBus() && !getCore().isEmptyCustomersQueueT1()){
            double boardingTime = getCore().getRndBoardToBus().next();
            getCore().plainEvent(new EndBoardingT1(getCore(), boardingTime, getMiniBus()));
        } else {
            getCore().plainEvent(new ArrivalMiniBusT2(getCore(), LengtT1ToT2, getMiniBus()));
        }
    }
    
}
