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
public class ArrivalMiniBusT1 extends AirCarEvent {

    public ArrivalMiniBusT1(AirCarCore core, double startTime, MiniBus minibus) {
        super(core, startTime, minibus);
    }

    @Override
    public void execute() {
//        System.out.print("Minibus: " + getMiniBus().getID()+ "| Prichod na T1 v cese: " + (getCore().getCurrentTime()));
//        System.out.println(" Pasažieri: " + getMiniBus().getCustomersInBus()+ " pocet: " + getMiniBus().getSize());
//        
        if(getCore().isEmptyCustomersQueueT1()){
            getMiniBus().setPosition("Cestujem z T1 do T2");
            getCore().plainEvent(new ArrivalMiniBusT2(getCore(), LengtT1ToT2, getMiniBus()));
        } else {
            getCore().plainEvent(new StartBoardingT1(getCore(), Now, getMiniBus(), getCore().getCustomerFromQueueT1()));
        }
    }
    
}
