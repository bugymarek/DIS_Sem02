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
    private double MinValue;
    private double MaxValue;
    private Random GenSeeds;
    private boolean Cooling;
    private boolean Pause;

    public SimulationCore(boolean cooling) {
        this.CalendarEvents = new PriorityQueue<>();
        this.GenSeeds = new Random(100);
        this.CurrentTime = 0;
        this.Command = null;
        this.Runnable = true;
        this.Cooling = cooling;
        this.Pause = false;
        this.Step = 1;
        this.Wait = 1000;
    }

    public void doReprications(double count, double endSimulationTime) {
        this.ReplicationsCount = count;
        for (double i = 1; i <= count; i++) {
            this.CurrentExperiment = i;
            System.out.println(CurrentExperiment);
            beforeReplication();
            beforeSimulation();
            simulate(endSimulationTime);
            if (!Runnable) {
                break;
            }
            afterSimulation();
            if (this.Command != null) {
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
                    break;
                }
            }
        }
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
}
