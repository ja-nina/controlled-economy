/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlledeconomy.classes;

import java.util.Set;
import java.util.TreeSet;


/**
 *
 * @author Windows 10
 */
public class FactoryMarket<T extends Market>{
    String[] prefixes = {"CUR", "COM", "STO"};
    private int currencyIndex;
    private int commodityIndex;
    private int stockIndex;
    private String tem;
    
    public FactoryMarket(String prefix){
        
        currencyIndex = 0;
        commodityIndex = 0;
        stockIndex = 0;
        //Faker faker = new Faker();
    }

    public FactoryMarket(int commodityIndex, int currencyIndex, int stockIndex) {
        this.commodityIndex = commodityIndex;
        this.currencyIndex = currencyIndex;
        this.stockIndex = stockIndex;
    }

    //the id is a 8digit number with 3-letter prefix
    public T createMarket(int prefix){
        String id = prefixes[prefix - 1];
         switch(prefix){
            case 1:
                tem = Integer.toString(currencyIndex);
                id = id + "000000".substring(tem.length()) + tem;
                currencyIndex +=1;
                return (T) new CurrencyMarket(id, "City:\t" + NameGenerator.getCityName());
                
            case 2:
                tem = Integer.toString(commodityIndex);
                id = id + "000000".substring(tem.length()) + tem;
                commodityIndex +=1;
                return (T) new CommodityMarket(id, "City:\t" + NameGenerator.getCityName());
            
            case 3:
                tem = Integer.toString(stockIndex);
                id = id + "000000".substring(tem.length()) + tem;
                stockIndex +=1;
                return (T) new StockMarket(id, "City:\t" + NameGenerator.getCityName(), "chujdupa", "PLN", "manila", "lol");
            
            default:
                return null;
    }
         
             
}
    
    
    
}
