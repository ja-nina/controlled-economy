/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlledeconomy.classes;

/**
 *
 * @author Windows 10
 */

import com.mycompany.controlledeconomy.PrimaryController;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

public class User {
    private String name;
    private HashSet<CommodityMarket> commodityMarkets;
    private HashSet<CurrencyMarket> forexMarkets;
    private HashSet<StockMarket> stockMarkets;
    
    public ArrayList<Market> listOfMarkets;
    public ArrayList<Asset> listOfAssets;
    public ArrayList<Traider> listOfTraders;
    public FactoryTraider factoryTraider;
    public Historian historian;
    private int commodityMarketCount = 0;
    private int stockMarketCount = 0;
    private int currencyMarketCount = 0;
    public ObservableValue numberOfAssets;
    public Set<String> displayedAssetsNames;
    //static Semaphore assetMutex = new Semaphore(1);
    public int quaziNumberOfTraders= 1;
    public MessengerValue TPM;
    public MessengerValue BBratio;
    public long programStartTime;
    private static User instance;
    static{
        try{
            instance = new User();
        }catch(Exception e){
            throw new RuntimeException("Exception occured in creating singleton instance");
        }
    }
    
    public static User getInstance(){
        return instance;
    }
    
    public User(){
        displayedAssetsNames = new HashSet<String>();
        listOfMarkets = new ArrayList<Market>();
        listOfAssets = new ArrayList<Asset>();
        listOfTraders = new ArrayList<Traider>();
        numberOfAssets = new ObservableValue(0, 10);
        BBratio = new MessengerValue(50);
        TPM = new MessengerValue(60);
        factoryTraider = new FactoryTraider(this, numberOfAssets, BBratio);
        numberOfAssets.addObserver(factoryTraider);
        historian = new Historian();
        Thread thread = new Thread(historian);
        thread.start();
        programStartTime = System.currentTimeMillis();

        
        
    }
    
    
    public synchronized boolean isAssetDisplayed(String name) throws InterruptedException{
        //assetMutex.acquire();
        Boolean res = displayedAssetsNames.contains(name);
        //assetMutex.release();
        return res;
    }
    
    public synchronized void addAssetToDisplayed(String name) throws InterruptedException{
        //assetMutex.acquire();
        displayedAssetsNames.add(name);
        //assetMutex.release();
    }
    
    public synchronized void removeAssetFromDisplayed(String name) throws InterruptedException{
        //assetMutex.acquire();
        displayedAssetsNames.remove(name);
        //assetMutex.release();
    }
    
    public void crateMarket() {}
    public void createCompany() {}
    public void createCurrency() {}
    public void createCommodity() {}
    public void createIndex(){}
    public void createInvestor(){}
    public void createFund(){}

    //i am not quite sure how to implement the factories(or if there is sense doing that),
    //so i decided to for now omit these classes.

    public void setBearBull() {}

    public void setTransactionsPerMinute() {}
    
    
    //---- getters and setters
    public void createStockMarket(){
        this.stockMarketCount += 1;
    }
    
    public void createCommodityMarket(){
        this.commodityMarketCount += 1;  
    }
    
    public void createCurrencyMarket(){
        this.currencyMarketCount += 1;
    }
    
    public int getCurrencyMarketCount(){
        return currencyMarketCount;
    }
    
    public int getStockMarketCount(){
        return stockMarketCount;
    }
    
    public int getCommodityMarketCount(){
        return commodityMarketCount;
    }
    
    public synchronized void addAsset(Asset asset) throws InterruptedException{
        //assetMutex.acquire();
        this.listOfAssets.add(asset);
        this.numberOfAssets.increase();
        //assetMutex.release();
        
    }
    
    public synchronized void addFund(FundParticipationUnit fund){
        this.listOfAssets.add(fund);
        System.out.println("lol");
        
    }
    
    public Asset getRandomAsset(){
        int index = (int)(Math.random() * listOfAssets.size());
        return listOfAssets.get(index);
    }
    
    public void setNumberOfTraders(int n){
        this.quaziNumberOfTraders = n;
    }
    
    
    public Share getShare(){
        for(Market market: listOfMarkets){
            if(market instanceof StockMarket){
                if(!((StockMarket) market).isMarketEmpty()){
                    return ((StockMarket) market).getRandomShare();
                }
            }
        }
        return null;
    }
    
    
    void computeHistory(){
        
        
    }
}
