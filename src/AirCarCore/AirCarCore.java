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
import java.util.ArrayList;

/**
 *
 * @author Bugy
 */
public class AirCarCore extends SimulationCore {

    private ArrayList<Operator> ArrOperators;
    private LinkedList<Customer> CustomersQueueT1;
    private LinkedList<Customer> CustomersQueueT2;
    private LinkedList<Customer> CustomersQueueRental;
    private double sumWaitingTimes;
    private double customersCount;
    private final ExponentialDistribution RndArrivalT1;
    private final ExponentialDistribution RndArrivalT2;
    private final UniformRangeDistribution RndBoardToBus;
    private final UniformRangeDistribution RndDropFromBus;
    private final UniformRangeDistribution RndOperating;
    private double sumAllWaitingTimes;

    public AirCarCore(int operatorsCount) {
        super();
        fillOperatorsArr(operatorsCount);
        this.RndArrivalT1 = super.getExponentialDistribution(LampdaArrivalT1); 
        this.RndArrivalT2 = super.getExponentialDistribution(LampdaArrivalT2);
        this.RndBoardToBus = super.getUniformRangeDistribution(BoardingUpperLimit, BoardingLowerLimit);
        this.RndDropFromBus = super.getUniformRangeDistribution(GetOutOfBusUpperLimit, GetOutOfBusLowerLimit);
        this.RndOperating = super.getUniformRangeDistribution(OperatingUpperLimit, OperatingLowerLimit);
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
        this.sumAllWaitingTimes += getResult()[0];
    }

    public double getSumAllWaitingTimes() {
        return this.sumAllWaitingTimes;
    } 

    private void fillOperatorsArr(int operators){
        this.ArrOperators = new ArrayList<Operator>();
        for (int i = 0; i < operators; i++) {
            this.ArrOperators.add(new Operator());
        }
    }
    
    public Operator getFreeOperator(){
        for (Operator o : this.ArrOperators) {
            if(!o.isOccupied()){
                return o;
            }
        }
        return null;
    }

    public void addWatingTime(double time) {
        this.sumWaitingTimes += time;
        this.customersCount += 1;
    }

    public Double[] getResult() {
        Double[] result = new Double[1];
        result[0] = this.sumWaitingTimes / this.customersCount;
        return result;
    }

    public ExponentialDistribution getRndArrivalT1() {
        return this.RndArrivalT1;
    }

    public ExponentialDistribution getRndArrivalT2() {
        return this.RndArrivalT2;
    }

    public UniformRangeDistribution getRndBoardToBus() {
        return this.RndBoardToBus;
    }

    public UniformRangeDistribution getRndDropFromBus() {
        return this.RndDropFromBus;
    }

    public UniformRangeDistribution getRndOperating() {
        return this.RndOperating;
    }
    
    public void addToCustomersQueueT1(Customer cus){
        this.CustomersQueueT1.add(cus);
    }
    
    public void addToCustomersQueueT2(Customer cus){
        this.CustomersQueueT2.add(cus);
    }
    
    public void addToCustomersQueueRental(Customer cus){
        this.CustomersQueueRental.add(cus);
    }
    
    public Customer getCustomerFromQueueT1(){
        return this.CustomersQueueT1.poll();
    } 
    
    public Customer getCustomerFromQueueT2(){
        return this.CustomersQueueT2.poll();
    }
     
    public Customer getCustomerFromQueueRental(){
        return this.CustomersQueueRental.poll();
    }
    
    public boolean isEmptyCustomersQueueT1(){
        return this.CustomersQueueT1.isEmpty();
    }
    
    public boolean isEmptyCustomersQueueT2(){
        return this.CustomersQueueT2.isEmpty();
    }
    
    public boolean isEmptyCustomersQueueRental(){
        return this.CustomersQueueRental.isEmpty();
    }
}
