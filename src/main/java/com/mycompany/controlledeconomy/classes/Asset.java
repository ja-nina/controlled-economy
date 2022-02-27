/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlledeconomy.classes;

import static java.lang.Math.abs;
import static java.lang.Math.min;
import static java.lang.Math.sqrt;
import java.util.Arrays;
import java.util.concurrent.Semaphore;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Windows 10
 */
public class Asset {
    
    public volatile XYChart.Series<Long, Double> history;
    public volatile XYChart.Series<Long, Double> displayed;
    protected int numberOfUnits;
    protected volatile double[] interestMetric;
    public long startingTime;
    public double influence;
    public double currentPrice;
    public double value;
    private int lastSecond = 0;
    public float getMargin(){return 0;}
    public void drawPlot(){}
    public synchronized double getValue() throws InterruptedException, InterruptedException{return value;}
    public String getName(){ return "";}
    public double computeMetric(){
        return 0.0;
    };
    static Semaphore mutex = new Semaphore(1);
    static Semaphore mutexBuySell = new Semaphore(1);
    
    public synchronized void computeHistory() throws InterruptedException {
        mutex.acquire();

        if(history.getData().size() == 0 || history.getData().get(history.getData().size() - 1).getXValue() < System.currentTimeMillis() - 250){
            history.getData().add(new XYChart.Data<>(System.currentTimeMillis(),
                                                    computePrice()));
        }
        //if (!User.getInstance().isAssetDisplayed(this.getName()) && history.getData().size() > 105)
        //    history.getData().remove(0);
        
        mutex.release();
        
    }
    
    public synchronized double getPrice() throws InterruptedException {
        return computePrice();
    }
    
    public synchronized int getHistorySize(){
        return history.getData().size();
    }
    
     public synchronized void sell() throws InterruptedException {
        // TODO Auto-generated method stub
        int currentTime = (int) ((System.currentTimeMillis()/1000)%10);
        //if we want to buy sth but the thing can not be accessed it only increases the price
        mutexBuySell.acquire();
        if(lastSecond != currentTime){
            if(lastSecond != currentTime){
                zeroOutInterestMetric(currentTime);
            } 
        }
        this.interestMetric[currentTime] -=1;
        this.numberOfUnits +=1;
        mutexBuySell.release();
    }

    public synchronized Asset buy(double budget) throws InterruptedException {
        
        int currentTime = (int) ((((int) System.currentTimeMillis()/1000)%10 + 10)%10);
        //if we want to buy sth but the thing can not be accessed it only increases the price
        if(this.numberOfUnits <1){
            mutexBuySell.acquire();
                
            mutexBuySell.release();
            //System.out.println("null");
            return null;
    }
        
        //System.out.println("success");
        mutexBuySell.acquire();
        if(lastSecond != currentTime){
                zeroOutInterestMetric(currentTime);
            } 
        this.interestMetric[currentTime] += 0.1;
        mutexBuySell.release();
        return this;
        // TODO Auto-generated method stub
        
    }
    
    public void closeBuy() throws InterruptedException{
        int currentTime = (int) ((((int) System.currentTimeMillis()/1000)%10 + 10)%10);
        mutexBuySell.acquire();
        if(lastSecond != currentTime){
                zeroOutInterestMetric(currentTime);
            } 
        this.interestMetric[currentTime] += 1;
        this.numberOfUnits -= 1;
        mutexBuySell.release();
    }
    
    protected synchronized double computePrice() throws InterruptedException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //return currentPrice;
        int signum = 1;
        int prevprevLastTime = (int) ((long)(System.currentTimeMillis()/1000 - 3)%10 + 10)%10;
        int prevLastTime = (int) ((long)(System.currentTimeMillis()/1000 - 2)%10 + 10)%10;
        int lastTime = (int) ((long)(System.currentTimeMillis()/1000 - 1)%10 + 10)%10;
        double[] backup = Arrays.copyOf(interestMetric,10);
        Arrays.sort(backup);
        mutexBuySell.acquire();
        System.out.println("LAST TIME UNIT: " + interestMetric[lastTime] + " sum of interest: " + Arrays.stream(interestMetric).sum()+ " "
                + interestMetric[0] + " "
        + interestMetric[1] + " "
        + interestMetric[2] + " "
        + interestMetric[3] + " "
        + interestMetric[4] + " "
        + interestMetric[5] + " "
        + interestMetric[6] + " "
        + interestMetric[8] + " "
        + interestMetric[8] + " "
        + interestMetric[9] + " ");
        double sum = Arrays.stream(interestMetric).sum();
        double median = (backup[4]+ backup[5])/2;
        if(median <= 0.01 || sum <= 0.01){ signum = -1;}
        currentPrice = currentPrice + min(((interestMetric[lastTime]*2.5+ interestMetric[prevLastTime]*1.5 + interestMetric[prevprevLastTime] + 0.1)/5 
                - signum*sqrt(abs(sum*median/10))),1)*currentPrice*0.01
                + (Math.random() - 0.45)*0.05 * currentPrice;
        System.out.println(getName() + "current price: " + currentPrice);
        value = currentPrice;
        mutexBuySell.release();
        return currentPrice;
    }
    

    //public getPointInHistory



    private synchronized void zeroOutInterestMetric(int currentTime){
        
            lastSecond = currentTime;
            this.interestMetric[currentTime] = 0;
        
    }
    
    
}
