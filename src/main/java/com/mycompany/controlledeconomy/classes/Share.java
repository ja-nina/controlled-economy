/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlledeconomy.classes;

import static java.lang.Double.max;
import static java.lang.Double.min;
import java.util.ArrayList;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import javafx.collections.FXCollections;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Windows 10
 */

public class Share extends Stock{    
    public Company company;
    private Thread t;


    
    
    public Share(String companyID, String companyName, double startingPrice, int numberOfUnits){
        this.currentPrice = startingPrice;
        this.company = new Company(companyID,companyName, this);
        this.numberOfUnits = numberOfUnits;
        this.maxNumberOfUnits = numberOfUnits;
        this.startingTime = System.currentTimeMillis();
        this.history = new XYChart.Series<>();
        this.displayed = new XYChart.Series<>();
        this.interestMetric = new double[10];
        t =new Thread(company);
        t.start();

    }

    public String getCompanyName(){
        return company.getName();
    }
    
    public String getName(){
        return "Share " + company.getName();
    }
    
 
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + company.getName().hashCode();  
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
        Share other = (Share) obj;
        if (company.getName() != other.company.getName())
            return false;
        return true;
    }
    
    
    @Override
    public double computeMetric() {
        //System.out.println("metric for " + this.name +  max(0,min((double)DoubleStream.of(interestMetric).sum() + 1/(double)(User.getInstance().quaziNumberOfTraders), 1)));
        return max(0,min((double)10*DoubleStream.of(interestMetric).sum()/(double)(User.getInstance().quaziNumberOfTraders), 1));
    }
   
}
