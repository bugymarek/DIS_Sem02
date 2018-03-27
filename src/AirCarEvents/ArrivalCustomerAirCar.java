/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AirCarEvents;

import AirCarCore.AirCarCore;
import AirCarCore.AirCarEvent;
import AirCarCore.Customer;
import AirCarCore.Operator;
import static Constants.Constants.*;
/**
 *
 * @author Bugy
 */
public class ArrivalCustomerAirCar extends AirCarEvent{

    public ArrivalCustomerAirCar(AirCarCore core, double startTime, Customer customer) {
        super(core, startTime, customer);
    }

    @Override
    public void execute() {
        Operator operator = getCore().getFreeOperator();
        
        // nemoze sa stat ze budem predbiehat ludi v rade ?
        if(operator != null){
            getCore().plainEvent(new StartOperating(getCore(), Now, operator, getCustomer()));
        } else {
            getCore().addToCustomersQueueRental(getCustomer());
        }       
    }
    
}
