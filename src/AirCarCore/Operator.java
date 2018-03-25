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
public class Operator {
    private boolean Occupied;

    public Operator() {
        this.Occupied = false;
    }

    
    public boolean isOccupied() {
        return Occupied;
    }

    public void setOccupied(boolean Occupied) {
        this.Occupied = Occupied;
    }
    
}
