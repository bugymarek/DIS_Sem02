/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AirCarCore;

/**
 *
 * @author Bugy
 */
class Customer {
    private double ArrivalTime;
    private int ID;

    public Customer(int id) {
        this.ID = id;
    }

    public int getID() {
        return ID;
    }
    
    public void setID(int id){
        this.ID = id;
    }

    public void setArrivalTime(double ArrivalTime) {
        this.ArrivalTime = ArrivalTime;
    }
   
    public double getArrivalTime() {
        return ArrivalTime;
    }
    
}
