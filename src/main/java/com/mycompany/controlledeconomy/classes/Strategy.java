/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlledeconomy.classes;

/**
 *
 * @author Windows 10
 */


//does it need to be an observer tho? - nah
public class Strategy {
    //need some test to see if this works bc there is no need for strategy to ba an observer but it has to have info abt bb ratio
    private MessengerValue BBratio = null;
    //in order to make things faster i get an array of outpts of the activation function(sigmoid)
    //here i have to assume that the minimum unit on the bb slider is 5
    static int[] activationArray = {0,5,10,15,20,21,23,24,30,40,45,50,57,69,73,80,88,92,93,96,10};
    double[] historicMetric;
    double sumMetric = 0;
    int indexMetric = 0; 
    //BB ranges from 0 to 100;
    
    //constructor bull/(bull+bear)= bear is sell bull is buy
    public Strategy(MessengerValue BBratio){
        this.BBratio = BBratio;
        historicMetric = new double[10];
        
        
 
    }
    // given some metric from 1-100 of how good the commodity is 
    
    
    public boolean buy(double metric){
        //System.out.println("CURRENT BB:" + Double.toString(BBratio.getValue())+ " and final metric: "+ Double.toString(newMetric) + "decision : " +
        //Boolean.toString((1+ Math.random() - 0.5)*(activationArray[(int) newMetric*20])*BBratio.getValue() > 0.5));
        boolean res = (Math.random() + 0.5*((double)(activationArray[(int) (metric*20)])/100.0))*(((double) BBratio.getValue()/100)*0.7 + 0.3) > 0.5;
        //System.out.println("BUY res: " + res + " " + ((double)(activationArray[(int)( metric*20)])/100.0) + " " + (((double) (BBratio.getValue()/100.0))*0.7 + 0.3));
        return res;
        //return Math.random()*100 < BBratio.getValue();
    }
    
    public boolean sell(double metric){

        //System.out.println(" SELLING CURRENT BB:" + Double.toString(BBratio.getValue())+
        //" verdict: " + Boolean.toString((1+ 0.2*Math.random() - 0.1)*(activationArray[(int) newMetric*20])*(0.5 + BBratio.getValue()*0.5) < 0.5));
        boolean res = (0.5 + 0.5*Math.random() + ((double)(activationArray[(int) (metric*20)])/100.0))*(0.5 + 0.5*(double) BBratio.getValue()/100)< 0.5;
        //System.out.println("sell res: " + res);
        return res;
        //return Math.random()*100 > BBratio.getValue();
    }
    
    
    
    
    
}
