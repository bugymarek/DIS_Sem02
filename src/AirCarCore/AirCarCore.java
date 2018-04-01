/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AirCarCore;

import AirCarEvents.ArrivalCustomerT1;
import AirCarEvents.ArrivalCustomerT2;
import AirCarEvents.DepartureMiniBusAirCar;
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
    private double DepartureCustomersCount;
    private double ArrivalCustomersCount;
    private final ExponentialDistribution RndArrivalT1;
    private final ExponentialDistribution RndArrivalT2;
    private final UniformRangeDistribution RndBoardToBus;
    private final UniformRangeDistribution RndDropFromBus;
    private final UniformRangeDistribution RndOperating;
    private double sumAllWaitingTimes;
    private final int MiniBusCount;
    private ArrayList<MiniBus> ArrMiniBuses;
    private boolean StopArrivalCustomers;
    private double MinValue;
    private double MaxValue;
    
    public AirCarCore(int miniBusCount, int operatorsCount, boolean cooling) {
        super(cooling);
        this.MiniBusCount = miniBusCount;
        this.ArrMiniBuses = new ArrayList<>();
        fillOperatorsArr(operatorsCount);
        this.RndArrivalT1 = super.getExponentialDistribution(LampdaArrivalT1); 
        this.RndArrivalT2 = super.getExponentialDistribution(LampdaArrivalT2);
        this.RndBoardToBus = super.getUniformRangeDistribution(BoardingUpperLimit, BoardingLowerLimit);
        this.RndDropFromBus = super.getUniformRangeDistribution(GetOutOfBusUpperLimit, GetOutOfBusLowerLimit);
        this.RndOperating = super.getUniformRangeDistribution(OperatingUpperLimit, OperatingLowerLimit);
        this.sumAllWaitingTimes = 0;  
        this.ArrivalCustomersCount = 0;
        this.MinValue = Double.MAX_VALUE;
        this.MaxValue = 0;
        this.StopArrivalCustomers = false;
    }

    @Override
    public void beforeSimulation() {
        this.DepartureCustomersCount = 0;
        this.ArrivalCustomersCount = 0;
        this.sumWaitingTimes = 0;
        this.CustomersQueueT1 = new LinkedList<>();
        this.CustomersQueueT2 = new LinkedList<>();
        this.CustomersQueueRental = new LinkedList<>();
        this.ArrMiniBuses = new ArrayList<>();
        this.StopArrivalCustomers = false;
        for (int i = 0; i < MiniBusCount; i++) {
            MiniBus mb = new MiniBus(i);
            ArrMiniBuses.add(mb);
            plainEvent(new DepartureMiniBusAirCar(this, Now, mb));
        }
        for (Operator o : this.ArrOperators) {
            o.setOccupied(false);
        }
        plainEvent(new ArrivalCustomerT1(this, RndArrivalT1.next(), new Customer(0, "T1")));
        plainEvent(new ArrivalCustomerT2(this, RndArrivalT2.next(), new Customer(0, "T2")));
    }

    @Override
    public void afterSimulation() {
        this.sumAllWaitingTimes += getResult();
        setAverage1(getAverage1() + getResult());
        setAverage2(getAverage2() + Math.pow(getResult(), 2));
    }

    public double getSumAllWaitingTimes() {
        return this.sumAllWaitingTimes;
    } 

    private void fillOperatorsArr(int operators){
        this.ArrOperators = new ArrayList<>();
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
    
    public int getFreeOperatorsCount(){
        int count =0;
        for (Operator o : this.ArrOperators) {
            if(!o.isOccupied()){
                count++;
            }
        }
        return count;
    }

    public void addWatingTime(double time) {
        this.sumWaitingTimes += time;
        this.DepartureCustomersCount ++;
    }

    public double getResult() {
        if(DepartureCustomersCount == 0){
            return 0;
        }else {
            return this.sumWaitingTimes / this.DepartureCustomersCount;
        }   
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
    
    public int getCustomerQueueT1Size(){
        return this.CustomersQueueT1.size();
    }
    
    public int getCustomerQueueT2Size(){
        return this.CustomersQueueT2.size();
    }
    
    public int getCustomerQueueRentalSize(){
        return this.CustomersQueueRental.size();
    }

    public double getDepartureCustomersCount() {
        return DepartureCustomersCount;
    }

    public double getArrivalCustomersCount() {
        return ArrivalCustomersCount;
    }
    
    public void incrementArrivalCustomersCount(){
        ArrivalCustomersCount ++;
    }
    
    public int getMiniBusesCount(){
        return ArrMiniBuses.size();
    }
    
    public MiniBus getMinibus(int index){
        return ArrMiniBuses.get(index);
    }

    public void setStopArrivalCustomers(boolean StopArrivalCustomers) {
        this.StopArrivalCustomers = StopArrivalCustomers;
    }

    public boolean isStopArrivalCustomers() {
        return StopArrivalCustomers;
    }

    public double getMaxValue() {
        return MaxValue;
    }

    public double getMinValue() {
        return MinValue;
    }
    
    public double getCurrentMean(){
       return sumAllWaitingTimes / getCurrentExperiment();
    }
    
    
    public String getInterval(double replications) {
        double s = Math.sqrt(getAverage2() / replications - Math.pow(getAverage1() / replications, 2));
        double min = getAverage1() / replications - Interval * s / Math.sqrt(replications - 1);
        double max = getAverage1() / replications + Interval * s / Math.sqrt(replications - 1);
        min = min/60.0;
        max = max/60.0;
        return "<" + String.format("%.5f", min) + " ; " + String.format("%.5f", max) + ">";
    }
    
    public void setMinMax(){
        if(getCurrentMean() < MinValue){
            MinValue = getCurrentMean();
        }
        if(getCurrentMean() > MaxValue){
            MaxValue = getCurrentMean();
        }
        if(MinValue == MaxValue){
            MaxValue += 0.000000000001;
        }          
    }
}
