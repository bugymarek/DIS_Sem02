/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import java.util.LinkedList;

/**
 *
 * @author Bugy
 */
public class Tests {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LinkedList<Integer> l = new LinkedList<>();
        l.add(3);
        l.add(2);
        l.add(6);
        System.out.println(l.poll());
        System.out.println(l.size());
        System.out.println(l.poll());
        System.out.println(l.size());
        System.out.println(l.poll());
        System.out.println(l.size());
        System.out.println(l.poll());
    }   
    
}
