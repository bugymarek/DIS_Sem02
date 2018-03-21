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
public abstract class Event implements Comparable<Event> {
    private double StartTime;
    private SimulationCore Core;
    
    public Event(SimulationCore core, double startTime ) {
        this.Core = core;
        this.StartTime = core.getCurrentTime() + startTime;
    }

    public SimulationCore getCore() {
        return Core;
    }

    public void setStartTime(double StartTime) {
        this.StartTime = StartTime;
    }

    public void setCore(SimulationCore Core) {
        this.Core = Core;
    }
    
    @Override
    public int compareTo(Event e) {        
        return Double.compare(StartTime,e.getStartTime());
       
    }

    public double getStartTime() {
        return StartTime;
    }
       
    public abstract void execute();
}
