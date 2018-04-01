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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bugy
 */
public abstract class SimulationCore {

    private double CurrentTime;
    private double Step;
    private long Wait;
    private PriorityQueue<Event> CalendarEvents;
    private double CurrentExperiment;
    private Command Command;
    private boolean Runnable;
    private double ReplicationsCount;
    private Random GenSeeds;
    private boolean Cooling;
    private boolean Pause;
    private final int Modulo = 1;
    private double EndSimulationTime;
    private double Average1;
    private double Average2;
    private boolean ReplicationDone;

    public SimulationCore(boolean cooling) {
        this.CalendarEvents = new PriorityQueue<>();
        this.GenSeeds = new Random(100);
        this.CurrentTime = 0;
        this.Command = null;
        this.Runnable = true;
        this.Cooling = cooling;
        this.Pause = false;
        this.Step = 1000;
        this.Wait = 1;  
        resetInterval();
        
    }

    public void doReprications(double count, double endSimulationTime) {
        this.EndSimulationTime = endSimulationTime;
        this.ReplicationsCount = count;
        resetInterval();
        for (double i = 1; i <= count; i++) {
            this.ReplicationDone = false;
            this.CurrentExperiment = i;
            System.out.println(CurrentExperiment);
            beforeReplication();
            beforeSimulation();
            simulate(endSimulationTime);
            if (!Runnable) {
                break;
            }
            afterSimulation();
            //setAverages();
            if (this.Command != null && i%Modulo == 0) {
                Command.run();
            }
        }
        //getResultsForStatistics();

    }

    private void simulate(double endSimulationTime){
        Event temporaryEvent;
        while ((Cooling || CurrentTime <= endSimulationTime) && !CalendarEvents.isEmpty() && Runnable) {
            temporaryEvent = CalendarEvents.poll();
            CurrentTime = temporaryEvent.getStartTime();
            if (!Cooling) {
                if (CurrentTime > endSimulationTime) {
                    break;
                }
            }
            temporaryEvent.execute();
            while (Pause) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SimulationCore.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (!Runnable) {
                    return;
                }
            }
        }
        this.ReplicationDone = true;
    }

    /**
     *
     * @param e
     * @return
     */
    public boolean plainEvent(Event e) {
        return CalendarEvents.add(e);
    }

    public PriorityQueue<Event> getCalendarEvents() {
        return CalendarEvents;
    }
    
    public void clearCalendarEvents(){
        CalendarEvents.clear();
    }

    public double getCurrentTime() {
        return CurrentTime;
    }

    public void step() {
        Command.run();
    }

    public double getStep() {
        return Step;
    }

    public long getWait() {
        return Wait;
    }

    public void setStep(double Step) {
        this.Step = Step;
    }

    public void setWait(long Wait) {
        this.Wait = Wait;
    }

    public void setPause(boolean Pause) {
        this.Pause = Pause;
    }

    public boolean isPause() {
        return Pause;
    }

    public void setRunnable(boolean Runnable) {
        this.Runnable = Runnable;
    }

    public boolean isRunnable() {
        return Runnable;
    }
    
    public void startSteps() {
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

    public ExponentialDistribution getExponentialDistribution(double lampda) {
        return new ExponentialDistribution(new Random(GenSeeds.nextInt()), lampda);
    }

    public UniformRangeDistribution getUniformRangeDistribution(double upperLimit, double lowerLimit) {
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

    public double getEndSimulationTime() {
        return EndSimulationTime;
    }

    public void setCooling(boolean Cooling) {
        this.Cooling = Cooling;
    }
    
    private void resetInterval(){
        this.Average1 = 0.0;
        this.Average2 = 0.0;
    }

    private void setAverages() {
        this.Average1 += getCurrentTime();
        this.Average2 += Math.pow(getCurrentTime(), 2);
    }

    public double getAverage1() {
        return Average1;
    }

    public double getAverage2() {
        return Average2;
    }

    public void setAverage1(double Average1) {
        this.Average1 = Average1;
    }

    public void setAverage2(double Average2) {
        this.Average2 = Average2;
    }

    public boolean isReplicationDone() {
        return ReplicationDone;
    }
    
}
