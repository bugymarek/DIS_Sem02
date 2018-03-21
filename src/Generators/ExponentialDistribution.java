/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generators;

import java.util.Random;

/**
 *
 * @author Bugy
 */
public class ExponentialDistribution implements IRandom{
    private final Random Rnd;
    private final double Lampda;

    public ExponentialDistribution(Random Rnd, double Lampda) {
        this.Rnd = Rnd;
        this.Lampda = Lampda;
    }

    @Override
    public double next() {
         return  Math.log(1-Rnd.nextDouble())/(-Lampda);
    }  
}
