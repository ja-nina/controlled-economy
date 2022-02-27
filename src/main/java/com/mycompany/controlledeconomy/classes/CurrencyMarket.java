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

public class CurrencyMarket implements Market{
    private float Beerbull;
    private String ID;
    private String name;
    private HashSet<Currency> Currencies;
    private int TPM;
    private int currentIndex;
    
    
    public CurrencyMarket(String ID, String name){
        this.ID = ID;
        this.name = name;
        this.Currencies = new HashSet<Currency>();
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
        return this.name;
    }

    @Override
    public String getID() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public void addAsset(Asset currency){
        Currencies.add((Currency) currency);
    }
    
    @Override
    public void addToIndex() {
        currentIndex +=1;
    }


}
