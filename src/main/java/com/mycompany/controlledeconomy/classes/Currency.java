/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlledeconomy.classes;

import static java.lang.Double.max;
import static java.lang.Double.min;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Windows 10
 */

public final class Currency extends Asset{
    private String name;
    private float temp;
    private float baseExchangeRate;
    private ArrayList<Integer> historyx;
    private ArrayList<Double> historyy;

    // price calculated by demand only
    public Currency(String name, float baseExchangeRate, double startingPrice){
        this.name = name;
        this.baseExchangeRate = baseExchangeRate;
        this.currentPrice = startingPrice;
        this.startingTime = System.currentTimeMillis();
        this.history = new XYChart.Series<>();
        this.displayed = new XYChart.Series<>();
        this.interestMetric = new double[10];
        this.numberOfUnits = (int) (Math.random()*1000);
    }
    public float getCurrentExchangeRate() {
        return 0;
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
        return max(0,min((double)10*DoubleStream.of(interestMetric).sum()/(double)(User.getInstance().quaziNumberOfTraders), 1));
    }
    
        @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + name.hashCode();  
        return result;
    }
 
    //Compare only account numbers
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Currency other = (Currency) obj;
        if (name != other.name)
            return false;
        return true;
    }
    
    //buyout -> makes company buy out all existing shares
    
    
}
