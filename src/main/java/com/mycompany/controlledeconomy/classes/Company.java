/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlledeconomy.classes;

/**
 *
 * @author Windows 10
 */


import java.util.Date;
import java.util.Random;

public class Company implements Runnable{
    private final String name;
    private final String ID;
    private float ipoShareValue;
    private final Date ipoDate;
    private final float openingPrice;
    private float currentPrice;
    private float maximalPrice;
    private double profit;
    private double revenue;
    private double capital;
    private double traidingVolume;
    private double totalSales;
    public Share share;
    boolean running = true;
    boolean buyout;

    public Company(String ID,String name, Share share){
        Random rand = new Random();
        this.name = name;
        this.ID = ID;
        this.ipoShareValue = rand.nextInt(60000);
        long ub = System.currentTimeMillis();
        this.ipoDate = new Date(System.currentTimeMillis() -  rand.nextLong());
        this.openingPrice = rand.nextInt(3000);
        this.capital = rand.nextInt(500000000);
        this.share = share;
        this.buyout = false;
    }

    //GETTERS AND SETTERS

    public String getName() {
        return name;
    }
   
    
    public float getIpoShareValue() {
        return ipoShareValue;
    }

    public void setIpoShareValue(float ipoShareValue) {
        this.ipoShareValue = ipoShareValue;
    }

    public Date getIpoDate() {
        return ipoDate;
    }

    public float getOpeningPrice() {
        return openingPrice;
    }


    public float getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(float currentPrice) {
        this.currentPrice = currentPrice;
    }

    public float getMaximalPrice() {
        return maximalPrice;
    }

    public void setMaximalPrice(float maximalPrice) {
        this.maximalPrice = maximalPrice;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public double getTraidingVolume() {
        return traidingVolume;
    }

    public void setTraidingVolume(double traidingVolume) {
        this.traidingVolume = traidingVolume;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }

    public void generateProfit(){}


    @Override
    public void run() {
        
        while(running) {
            System.out.println("company generating profit buyout" + buyout);
            try {
                Thread.sleep(5000);
                if(buyout == false && (int)(Math.random()*100) == 50){
                    buyout = true;
                    System.out.println(getName() + " AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\nAAAAAAAAAAAAAA\n FORCED BUYOUT");
                    forceBuyout();
                    
                }
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
    
    public void forceBuyout() throws InterruptedException{
        Share varShare ;
        Boolean stop = false;
        while(capital > 0 && stop == false){
            varShare = (Share) this.share.buy(capital);
            if(varShare!= null){
                capital -= this.share.getValue();
                this.share.closeBuy();
            }
            else{
                stop = false;
            }
        }
        
    }
}
