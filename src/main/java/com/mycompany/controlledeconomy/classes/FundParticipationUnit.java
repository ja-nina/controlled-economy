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
import java.util.List;
import java.util.stream.IntStream;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Windows 10
 */
public class FundParticipationUnit extends Stock {
    public List<Company> companies;
    public List<Integer> numberOfShares;
    private String initialsOfFund;
    private int id;

    public FundParticipationUnit(String initials, String FundName){
        System.out.println("CONSTRUCTOR  FUNDPARTICIPATIONUNIT");
        this.startingTime = System.currentTimeMillis();
        this.history = new XYChart.Series<>();
        this.displayed = new XYChart.Series<>();
        this.interestMetric = new double[10];
        initialsOfFund= FundName;
        this.companies = new ArrayList<Company>();
        this.numberOfShares = new ArrayList<Integer>();
        this.numberOfUnits = 0;
    };
    //constructor here
    public void addCompany(Company company){
        this.companies.add(company);
    }
    
    @Override 
    public String getName(){
        return "Fund unit " + initialsOfFund;
    }
    

    
    
    public void setNumberOfShares(int n){
        numberOfShares.add(n);
        numberOfUnits += n;
        System.out.println("added");
    }
    @Override
    protected synchronized double computePrice() throws InterruptedException {
        // sum value of shares + add margin 1%
        double price = 0; int i = 0; int size;
        //System.out.println("computing :" + getName());
        for(Company company: this.companies){
            //price += this.numberOfShares.get(i) * company.share.computePrice();
            ///System.out.println("computing :" + getName());
            size = company.share.history.getData().size();
            if(size> 2){
            price +=  company.share.history.getData().get( size - 2).getYValue();
            }
        }
        return price*1.01;}
    //return 100;}
    
    
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.getName().hashCode();  
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
        FundParticipationUnit other = (FundParticipationUnit) obj;
        if (this.getName() != other.getName())
            return false;
        return true;
    }
    
    
    @Override
    public double computeMetric() {
        return 0.5;
    }
}
