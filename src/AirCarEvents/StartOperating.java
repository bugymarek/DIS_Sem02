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

/**
 *
 * @author Bugy
 */
public class StartOperating extends AirCarEvent {

    public StartOperating(AirCarCore core, double startTime, Operator operator, Customer customer) {
        super(core, startTime, operator, customer);
    }

    @Override
    public void execute() {
        getOperator().setOccupied(true);
        double operatingTime = getCore().getRndOperating().next();
        getCore().plainEvent(new EndOperating(getCore(), operatingTime, getOperator(), getCustomer()));
    }
    
}
