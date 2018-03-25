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
public class ArrivalCustomerT1 extends AirCarEvent{

    public ArrivalCustomerT1(AirCarCore core, double startTime, Customer customer) {
        super(core, startTime, customer);
    }

    @Override
    public void execute() {
        getCustomer().setArrivalTime(getCore().getCurrentTime());
        
        //System.out.println("Zakaznik: " + getCustomer().getTerminalAndID() + "| Prichod zakaznika v cese: " + (getCore().getCurrentTime()));
        getCore().addToCustomersQueueT1(getCustomer());
        
        double arrivalTime = getCore().getRndArrivalT1().next();
        Customer cus = new Customer(getCustomer().getID()+1, "T1");
        getCore().plainEvent(new ArrivalCustomerT1(getCore(), arrivalTime, cus));
    }
    
}
