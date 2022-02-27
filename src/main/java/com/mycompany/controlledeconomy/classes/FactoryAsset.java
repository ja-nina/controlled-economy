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
public class FactoryAsset<T extends Asset>{

    private static String com = "COM";
    private static String sto = "STO";
    private static String cur = "CUR";
    private static int companyIndex = 0;
    private static int commodityIndex = 0;
    private static int currencyIndex = 0;
    
    public static int getNumberOfStocks(){
        return companyIndex;
    }
    
      //String[] prefixes = {"CUR", "COM", "STO"};
//    private int currencyIndex;
//    private int commodityIndex;
//    private int stockIndex;
//    private String tem;
//    
//    public FactoryAsset(String prefix){
//        
//        currencyIndex = 0;
//        commodityIndex = 0;
//        stockIndex = 0;
//        //Faker faker = new Faker();
//    }
//
//    public FactoryAsset() {
//        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    //the id is a 8digit number with 3-letter prefix
    public static Asset createAsset(Market market){
        System.out.println(market.getID().substring(0, 3));
        
        switch(market.getID().substring(0, 3)){
            case "COM":
                commodityIndex +=1;
                String commodityID = "COMMODITYID" + Integer.toString(commodityIndex);
                Commodity commodity = new Commodity(NameGenerator.getCommodityName(), commodityID, "YUEN","BLA", 1000* Math.random(), (int) (1000* Math.random()));
                
                market.addAsset(commodity);
                market.addToIndex();
                return commodity;
                
            case "CUR":
                //i guess no ids on currency markets
                currencyIndex +=1;
                String currencyID = NameGenerator.getCoinName() + " " + Integer.toString(currencyIndex);
                Currency currency = new Currency(currencyID, (float) 0.01, 1000* Math.random());
                market.addAsset(currency);
                market.addToIndex();
                return currency;
            
            case "STO":
                //one first needs to instantiate a company
                companyIndex +=1;
                String companyID = "COMPANY" + " " + Integer.toString(companyIndex);
                String companyName = NameGenerator.getCompanyName();
                Share share = new Share(companyID,companyName, 1000* Math.random(), (int) (1000* Math.random()));
                //the constructor of share automatically starts compny threat
                market.addAsset(share);
                market.addToIndex();
                return share;
            
            default:
                return null;
    }
}
}