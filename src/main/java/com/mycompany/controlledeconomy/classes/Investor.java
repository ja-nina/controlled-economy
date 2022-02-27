/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlledeconomy.classes;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Windows 10
 */

public class Investor implements Traider, Runnable{
    private String firstName;
    private String lastName;
    private String ID;
    private double budget;
    private boolean attitute;
    private Strategy strategy = null;
    private HashMap<Asset,Integer> wallet;
    static Semaphore mutex = new Semaphore(1); 

    public Investor(String firstName, String lastName, String ID, double budget, Strategy strategy) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ID = ID;
        this.budget = budget;
        this.strategy = strategy;
        wallet = new HashMap<Asset,Integer>();
    }

    public String getName(){
        return firstName + " " + lastName;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public HashMap<Asset,Integer> getWallet(){
        return wallet;
    }
    
    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }


    @Override
    public void run() {
        boolean running = true;
        int quantityAsset;
        
        Random rand = new Random();
        while(running) {
            try {
                //semophore for buying asset required here
                //System.out.println("wow");
                mutex.acquire();
                Asset asset = User.getInstance().getRandomAsset().buy(budget);
                System.out.println("asset null? :" + asset==null + " ");
                if(asset != null && strategy.buy(asset.computeMetric())){
                    //System.out.println("asset is not null");
                if(asset.buy(budget) != null){
                   
                    //System.out.println("TRADER AAAAAAAAAAAAAAAaaa" + firstName + " WALLET SIZE " + Integer.toString(wallet.size()));
                    budget -= asset.getValue(); //i know this is ricky but fuck it 
                    if(wallet.containsKey(asset)){wallet.put(asset, wallet.get(asset) + 1);
                    }else{wallet.put(asset,1);}
                    asset.closeBuy();
                }
                }
                mutex.release();
                Asset[] values =  wallet.keySet().toArray( new Asset[wallet.size()]);
                //System.out.println("wallet size:" + Integer.toString(wallet.size()));
                if(wallet.size() > 0){
                asset = values[rand.nextInt(wallet.size())];
                mutex.acquire();
                if(strategy.sell(asset.computeMetric())){
                    asset.sell();
                    quantityAsset = wallet.get(asset);
                    if(quantityAsset > 1){
                        wallet.replace(asset, quantityAsset - 1);
                    }
                    else{
                        wallet.remove(asset);
                    }
                    //System.out.println("sold " + asset.getName());
                    asset.sell();
                    
                }
                mutex.release();
                }
                Thread.sleep(1000*60/User.getInstance().TPM.getValue());
            }catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        
        
    }

}
