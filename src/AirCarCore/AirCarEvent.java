/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AirCarCore;

import Core.Event;

/**
 *
 * @author Bugy
 */
public abstract class AirCarEvent extends Event {
    private Customer Customer;
    private Operator Operator;
    private AirCarCore Core; 
    private MiniBus MiniBus;
    
    public AirCarEvent(AirCarCore core, double startTime, Customer customer) {
        super(core, startTime);
        this.Core = core;
        this.Customer = customer;
    } 
    
    public AirCarEvent(AirCarCore core, double startTime, Operator operator, Customer customer) {
        super(core, startTime);
        this.Core = core;
        this.Operator = operator;
        this.Customer = customer;
    }
    
    public AirCarEvent(AirCarCore core, double startTime, MiniBus minibus) {
        super(core, startTime);
        this.Core = core;
        this.MiniBus = minibus;
    }
    
    public AirCarEvent(AirCarCore core, double startTime, MiniBus minibus, Customer customer) {
        super(core, startTime);
        this.Core = core;
        this.MiniBus = minibus;
        this.Customer = customer;
    }

    public Customer getCustomer() {
        return Customer;
    }

    @Override
    public AirCarCore getCore() {
        return Core;
    }

    public Operator getOperator() {
        return Operator;
    }

    public MiniBus getMiniBus() {
        return MiniBus;
    }

    public void setCustomer(Customer Customer) {
        this.Customer = Customer;
    }

    public void setOperator(Operator Operator) {
        this.Operator = Operator;
    }

    public void setMiniBus(MiniBus MiniBus) {
        this.MiniBus = MiniBus;
    }
    
    @Override
    public abstract void execute();
    
}
