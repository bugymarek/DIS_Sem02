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
public class Customer {
    private double ArrivalTime;
    private int ID;
    private String Terminal;

    public Customer(int id, String terminal) {
        this.ID = id;
        this.Terminal = terminal;
    }

    public int getID() {
        return ID;
    }
    
    public void setID(int id){
        this.ID = id;
    }
    
    public String getTerminalAndID(){
        return this.Terminal + "/" + this.ID;
    }

    public void setArrivalTime(double ArrivalTime) {
        this.ArrivalTime = ArrivalTime;
    }
   
    public double getArrivalTime() {
        return ArrivalTime;
    }
    
}
