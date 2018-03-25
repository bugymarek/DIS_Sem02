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
    private int ID;

    public MiniBus(int id) {
        this.Customers = new LinkedList<>();
        this.ID = id;
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

    public int getID() {
        return ID;
    }
    
    public int getSize(){
        return Customers.size();
    }
    
    public String getCustomersInBus(){
        String arr = "";
        for (Customer Customer : Customers) {
              arr += " ," + Customer.getTerminalAndID();
        }
        return arr;
    }
}
