/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AirCarCore;

import Core.SimulationCore;
import Generators.ExponentialDistribution;
import Generators.UniformRangeDistribution;
import java.util.LinkedList;
import static Constants.Constants.*;

/**
 *
 * @author Bugy
 */
public class AirCarCore extends SimulationCore {

    private LinkedList<Customer> CustomersQueueT1;
    private LinkedList<Customer> CustomersQueueT2;
    private LinkedList<Customer> CustomersQueueRental;
    private double sumWaitingTimes;
    private double customersCount;
    private final ExponentialDistribution RndArrivalT1;
    private final ExponentialDistribution RndArrivalT2;
    private final UniformRangeDistribution RndBoardToBus;
    private final UniformRangeDistribution RndDropFromBus;
    private final UniformRangeDistribution RndOccupied;
    private double sumAllWaitingTimes;

    public AirCarCore() {
        super();
        this.RndArrivalT1 = super.getExponentialDistribution(LampdaArrivalT1); 
        this.RndArrivalT2 = super.getExponentialDistribution(LampdaArrivalT2);
        this.RndBoardToBus = super.getUniformRangeDistribution(BoardingUpperLimit, BoardingLowerLimit);
        this.RndDropFromBus = super.getUniformRangeDistribution(DropingUpperLimit, DropingLowerLimit);
        this.RndOccupied = super.getUniformRangeDistribution(OccupiedUpperLimit, OccupiedLowerLimit);
        this.sumAllWaitingTimes = 0;
    }

    @Override
    public void beforeSimulation() {
        this.customersCount = 0;
        this.sumWaitingTimes = 0;
        this.CustomersQueueT1 = new LinkedList<>();
        this.CustomersQueueT2 = new LinkedList<>();
        this.CustomersQueueRental = new LinkedList<>();
    }

    @Override
    public void afterSimulation() {
        sumAllWaitingTimes += getResult()[0];
    }

    public double getSumAllWaitingTimes() {
        return sumAllWaitingTimes;
    } 


    public void addWatingTime(double time) {
        this.sumWaitingTimes += time;
        this.customersCount += 1;
    }

    public Double[] getResult() {
        Double[] result = new Double[1];
        result[0] = sumWaitingTimes / customersCount;
        return result;
    }

    public ExponentialDistribution getRndArrivalT1() {
        return RndArrivalT1;
    }

    public ExponentialDistribution getRndArrivalT2() {
        return RndArrivalT2;
    }

    public UniformRangeDistribution getRndBoardToBus() {
        return RndBoardToBus;
    }

    public UniformRangeDistribution getRndDropFromBus() {
        return RndDropFromBus;
    }

    public UniformRangeDistribution getRndOccupied() {
        return RndOccupied;
    }
    
    
}
