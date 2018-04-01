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
import java.util.ArrayList;
/**
 *
 * @author Bugy
 */
public class EndOperating extends AirCarEvent{

    public EndOperating(AirCarCore core, double startTime, Operator operator, Customer customer) {
        super(core, startTime, operator, customer);
    }

    @Override
    public void execute() {
        getCore().addWatingTime(getCore().getCurrentTime() - getCustomer().getArrivalTime());
        getOperator().setOccupied(false);
        if(getCore().isStopArrivalCustomers() && getCore().getArrivalCustomersCount() == getCore().getDepartureCustomersCount() ){
            getCore().clearCalendarEvents();
        }
        if(!getCore().isEmptyCustomersQueueRental()){
            getCore().plainEvent(new StartOperating(getCore(), Now, getOperator(), getCore().getCustomerFromQueueRental()));
        }
        
    }
    
}
