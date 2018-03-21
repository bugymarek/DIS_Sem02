/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

/**
 *
 * @author Bugy
 */
public class Stepper extends Event{
    private final double Step = 1;
    
    public Stepper(SimulationCore core, double startTime) {
        super(core, startTime);
    }

    @Override
    public void execute() {
        SimulationCore core = getCore();
        core.step();
        // mozno sleep]
        //double starTime = core.getCurrentTime() + Step;
        core.plainEvent(new Stepper(core, Step));
    }
    
}
