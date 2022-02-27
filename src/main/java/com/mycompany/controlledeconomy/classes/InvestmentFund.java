/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlledeconomy.classes;

import static com.mycompany.controlledeconomy.classes.Investor.mutex;
import static java.lang.Thread.sleep;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author Windows 10
 */

public class InvestmentFund implements Traider, Runnable{
    private String name;
    private String initialsOfManager;
    private FundParticipationUnit fund;
    private String ID;
    private double budget;
    private Strategy strategy = null;
    private HashMap<Asset,Integer> wallet;
    private int numberOfShares = 0;
    
  
    
    public InvestmentFund(String name, String initialsOfManager, String ID, double budget, Strategy strategy) {
        System.out.println("Constructor FUND");
        this.name = name;
        this.initialsOfManager = initialsOfManager;
        this.ID = ID;
        this.budget = budget;
        this.strategy = strategy;
        wallet = new HashMap<Asset,Integer>();
 
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInitialsOfManager() {
        return initialsOfManager;
    }

    public void setInitialsOfManager(String initialsOfManager) {
        this.initialsOfManager = initialsOfManager;
    }

    public HashMap<Asset,Integer> getWallet(){
        return wallet;
    }
    

/// on creation investmentfund creates sharepack of maximum 12
//creates only if shares are present
//need an if when it comes to checking unber of stocks (has to be at least 12
    public FundParticipationUnit createFund() throws InterruptedException{
        System.out.println("creating fund");
        int numberOfShares = (int)Math.random()*4 + 2;
        int i =0;
        fund = new FundParticipationUnit(name, name);
        Asset share;
        
        while(i< numberOfShares &&  this.budget > 1000){
            //System.out.println("adding share");
            share = User.getInstance().getShare();
            //System.out.println("share " + share.getName());
            if(!fund.companies.contains(((Share)share).company)){
                share = share.buy(budget);
                budget -= share.getValue();
                if(share != null){
                    fund.addCompany(((Share)share).company);
                }
                int n =0;
                int howManyToBuy = (int) Math.random()*3 + 1;
                //System.out.println("how many " + howManyToBuy);
                while((n < howManyToBuy && (this.budget > 10000)) && share != null){
                    //System.out.println("buying a share for the fund");
                    this.budget -= share.getValue();
                    //System.out.println("nochmal buy " + budget);
                    if(n < howManyToBuy - 1){share =  share.buy(budget);share.closeBuy();}
                    //System.out.println("end buy  " + numberOfShares + " " + budget);
                    n = n + 1;
                }
                fund.setNumberOfShares(n);
                System.out.println("added");
            }
            //System.out.println("stop");
            i++;
            //System.out.println("stop2 "+ numberOfShares);
            }
        return fund;
        }
    

    @Override
    public void run() {
        boolean running = true;
        Random rand = new Random();
        int quantityAsset;
        try {
            //createFund();
            User.getInstance().addFund(createFund());
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        
        
        while(running) {
            try {
                //semophore for buying asset required here
                //System.out.println("wow");
                mutex.acquire();
                Asset asset = User.getInstance().getRandomAsset().buy(budget);
                if(asset != null && strategy.buy(asset.computeMetric())){
                
                if(asset.buy(budget) != null){
                   
                    //System.out.println("INVESTMENTFUND" + name + " WALLET SIZE " + Integer.toString(wallet.size()));
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
