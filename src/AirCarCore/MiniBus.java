/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AirCarCore;

import java.util.LinkedList;

/**
 *
 * @author Bugy
 */
public class MiniBus {
    private final LinkedList<Customer> Customers;

    public MiniBus() {
        this.Customers = new LinkedList<>();
    }
    
    public Customer getCustomerFromBus(){
        return Customers.poll();
    }
    
    public void addCustomerToBus(Customer cus){
        Customers.add(cus);
    }
    
    public boolean isEmpty(){
        return Customers.isEmpty();
    }
    
    public boolean isPlaceInBus(){
        return Customers.size()<12;
    }
}
