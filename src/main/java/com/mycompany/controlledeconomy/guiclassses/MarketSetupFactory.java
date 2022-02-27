/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlledeconomy.guiclassses;

import com.mycompany.controlledeconomy.classes.CommodityMarket;
import com.mycompany.controlledeconomy.classes.CurrencyMarket;
import com.mycompany.controlledeconomy.classes.Market;
import com.mycompany.controlledeconomy.classes.StockMarket;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 *
 * @author Windows 10
 */
public class MarketSetupFactory {
    
    public static String getAssetButtonName(Market market){
        System.out.println(market.getClass());
        if(market instanceof CommodityMarket){
            return "Create commodity";
        }
        if(market instanceof StockMarket){
            return "Create stock";
        }
        if(market instanceof CurrencyMarket){
            return "Create currency";
        }
        
        return "error";
    }
}
