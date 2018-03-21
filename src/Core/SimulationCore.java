/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core;

import Generators.ExponentialDistribution;
import Generators.UniformRangeDistribution;
import java.util.PriorityQueue;
import java.util.Random;

/**
 *
 * @author Bugy
 */
public abstract class SimulationCore {
    private double CurrentTime;
    private PriorityQueue<Event> CalendarEvents;
    private double CurrentExperiment;
    private double Success;
    private Command Command;
    private boolean Runnable;
    private double ReplicationsCount;
    private final int Modulo = 1;
    private double MinValue;
    private double MaxValue;
    private Random GenSeeds;
    
    public SimulationCore() {
        this.CalendarEvents = new PriorityQueue<>();
        this.GenSeeds = new Random(100);
        this.CurrentTime = 0;
        this.Command = null;
        this.Success = 0;
        this.Runnable = true;
    }  

    public void doReprications(double count, double endSimulationTime) {
        this.ReplicationsCount = count;
        for (double i = 1; i <= count; i++) {
            this.CurrentExperiment = i;
            System.out.println(CurrentExperiment);
            beforeReplication();
            beforeSimulation();
            simulate(endSimulationTime);
            if(!Runnable) break;
            afterSimulation();       
            if(this.Command != null && i%Modulo == 0) {
                Command.run();
            }
        }
        //getResultsForStatistics();
        
    }
    
    private void simulate(double endSimulationTime) {
       Event temporaryEvent;
       while(CurrentTime <= endSimulationTime && !CalendarEvents.isEmpty() && Runnable){
           temporaryEvent = CalendarEvents.poll();
           CurrentTime = temporaryEvent.getStartTime();
           if(CurrentTime > endSimulationTime) break;
           temporaryEvent.execute();
       }
    }
    
    public boolean plainEvent(Event e){
        return CalendarEvents.add(e);
    }

    public PriorityQueue<Event> getCalendarEvents() {
        return CalendarEvents;
    }
    
    public double getCurrentTime() {
        return CurrentTime;
    }
    
    public void step(){
        Command.run();
    }
    
    public void startSteps(){
        //plainEvent(new Stepper(this, CurrentTime));
    }
    public abstract void beforeSimulation();
    
    public abstract void afterSimulation();

    private void beforeReplication() {
        CalendarEvents = new PriorityQueue<>();
        CurrentTime = 0;
        startSteps();
    }

    public void setCommand(Command Command) {
        this.Command = Command;
    }
    
    public ExponentialDistribution getExponentialDistribution(double lampda){
        return new ExponentialDistribution(new Random(GenSeeds.nextInt()), lampda);
    }
    
    public UniformRangeDistribution getUniformRangeDistribution(double upperLimit, double lowerLimit){
            return new UniformRangeDistribution(upperLimit, lowerLimit, new Random(GenSeeds.nextInt()));
    }

    public double getReplicationsCount() {
        return ReplicationsCount;
    }

    public double getCurrentExperiment() {
        return CurrentExperiment;
    }

    private void getResultsForStatistics() {
        Command.run();
    }
}
