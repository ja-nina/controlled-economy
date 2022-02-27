/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlledeconomy.classes;

/**
 *
 * @author Windows 10
 */
import static java.lang.Double.max;
import static java.lang.Double.min;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import javafx.scene.chart.XYChart;

public class Commodity extends Asset{
    private String name;
    private String tradingUnit;
    private String tradingCurrency;
    private final int maxNumberOfUnits;
    private float transactionMargin;
    private String id; 
    
    
    
   //price calculated by demand only

    HashSet<String> set=new HashSet<String>();

    public Commodity(String name, String id, String tradingUnit, String tradingCurrency, double startingPrice, int numberOfUnits) {
        this.name = name;
        this.id = id;
        this.startingTime = System.currentTimeMillis();
        this.tradingUnit = tradingUnit;
        this.tradingCurrency = tradingCurrency;
        this.currentPrice = startingPrice;
        this.numberOfUnits = numberOfUnits;
        this.maxNumberOfUnits = numberOfUnits;
        this.history = new XYChart.Series<>();
        this.displayed = new XYChart.Series<>();
        this.interestMetric = new double[10];
        this.influence = 0;
        

    }
    
    @Override
    public double getValue() {
        // TODO Auto-generated method stub
        return currentPrice;
    }

    @Override
    public String getName() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return name;
    }

    @Override
    public double computeMetric() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //return currentPrice;

        //System.out.println("metric for " + this.name +  max(0,min((double)DoubleStream.of(interestMetric).sum() + 1/(double)(User.getInstance().quaziNumberOfTraders), 1)));
        return max(0,min((double)DoubleStream.of(interestMetric).sum()/(double)(User.getInstance().quaziNumberOfTraders), 1));
    }


    
    
    //Depends only on account number
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + name.hashCode();  
        return result;
    }
    
    //Compare only name numbers
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Commodity other = (Commodity) obj;
        if (name != other.name)
            return false;
        return true;
    }
    
    
}
