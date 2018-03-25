package Constants;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bugy
 */
public class Constants {
    
//  all constants are represented as minute unit
    
    public static double Now = 0;
  
    public static double LampdaArrivalT1 = 1.0 / (60.0 /43.0);
    public static double LampdaArrivalT2 = 1.0 / (60.0 /19.0);
    
    public static double OccupiedLowerLimit = 2.0;
    public static double OccupiedUpperLimit = 10.0;
    
    public static double BoardingLowerLimit = 10.0 / 60.0;
    public static double BoardingUpperLimit = 14.0 / 60.0;
    
    public static double DropingLowerLimit = 4.0 / 60.0;
    public static double DropingUpperLimit = 12.0 / 60.0;
    
    public static double LengtRentalToT1 = 60.0 / 35.0 * 6.4;
    public static double LengtT1ToT2 = 60.0 / 35.0 * 0.5;
    public static double LengtT2ToRental = 60.0 / 35.0 * 2.5;
    
    public static double Interval = 1.645; 
    
}
