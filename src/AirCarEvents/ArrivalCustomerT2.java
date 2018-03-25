/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AirCarEvents;

import AirCarCore.AirCarCore;
import AirCarCore.AirCarEvent;
import AirCarCore.Customer;


/**
 *
 * @author Bugy
 */
public class ArrivalCustomerT2 extends AirCarEvent{

    public ArrivalCustomerT2(AirCarCore core, double startTime, Customer customer) {
        super(core, startTime, customer);
    }

    @Override
    public void execute() {
        getCustomer().setArrivalTime(getCore().getCurrentTime());
        
//        System.out.println("Zakaznik: " + getCustomer().getTerminalAndID() + "| Prichod zakaznika v cese: " + (getCore().getCurrentTime()));
        getCore().addToCustomersQueueT2(getCustomer());
        
        double arrivalTime = getCore().getRndArrivalT2().next();
        Customer cus = new Customer(getCustomer().getID()+1, "T2");
        getCore().plainEvent(new ArrivalCustomerT2(getCore(), arrivalTime, cus));
    }
    
}
