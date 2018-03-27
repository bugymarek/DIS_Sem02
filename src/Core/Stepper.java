/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bugy
 */
public class Stepper extends Event{
    
    
    public Stepper(SimulationCore core, double startTime) {
        super(core, startTime);
    }

    @Override
    public void execute() {
        getCore().step();
        try {
            Thread.sleep(getCore().getWait());
        } catch (InterruptedException ex) {
            Logger.getLogger(Stepper.class.getName()).log(Level.SEVERE, null, ex);
        }
        getCore().plainEvent(new Stepper(getCore(), getCore().getStep()));
        System.out.println(getCore().getStep());
    }
    
}
