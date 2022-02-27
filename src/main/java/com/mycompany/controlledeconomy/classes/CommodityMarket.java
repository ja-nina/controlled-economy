/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlledeconomy.classes;

/**
 *
 * @author Windows 10
 */
import java.util.HashSet;
import java.util.List;

public class CommodityMarket implements Market{
    private String ID;
    private String name;
    private float Beerbull;
    private int TPM;
    private HashSet<Commodity> Commodities;
    private int currentIndex;

    public CommodityMarket(String ID, String name){
        this.ID = ID;
        this.name = name;
        this.Commodities = new HashSet<Commodity>();
    }
    public void addCommodity(){

    }
    @Override
    public void setTransactionsPerMinute(int tpm) {
        this.TPM = tpm;

    }

    @Override
    public void setBearBull() {

    }

    @Override
    public void notifyObservers() {

    }

    @Override
    public void registerObservers() {

    }

    @Override
    public void removeObservers() {

    }

    public void getBearBull() {

    }
    
    
    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return this.name;}
    
    @Override
    public String getID() {
        return this.ID;
    }

    @Override
    public void setName(String name) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.name = name;
    }

    @Override
    public int getCurrentIndex() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return currentIndex;
    }
    
    @Override
    public void addAsset(Asset commodity){
        Commodities.add((Commodity)commodity);
    }

    @Override
    public void addToIndex() {
        currentIndex +=1;
    }
    
    

}

