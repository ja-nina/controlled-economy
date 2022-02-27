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

public class StockMarket implements Market{
    private String ID;
    private String name;
    private String country;
    private String traidingCurrency;
    private String city;
    private String address;
    private float Beerbull;
    private float transactionMargin;
    private HashSet<Share> Indices;
    private int TPM;
    private int currentIndex;
    
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

    public StockMarket(String ID, String name, String country, String traidingCurrency, String city, String address) {
        this.ID = ID;
        this.name = name;
        this.country = country;
        this.traidingCurrency = traidingCurrency;
        this.city = city;
        this.address = address;
        this.Indices = new HashSet<Share>();
        
    }


    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getTraidingCurrency() {
        return traidingCurrency;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public float getBeerbull() {
        return Beerbull;
    }

    public float getTransactionMargin() {
        return transactionMargin;
    }

    public HashSet<Share> getIndices() {
        return Indices;
    }

    @Override
    public String getID() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return ID;
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
    public void addAsset(Asset stock){
        Indices.add((Share)stock);
    }

    @Override
    public void addToIndex() {
        currentIndex +=1;
    }
    
    
    public Boolean isMarketEmpty(){
        
        return Indices.size() == 0;
    }
    
    public Share getRandomShare(){
        
        return (Share) Indices.toArray()[(int) (Math.random()* Indices.size())];
    }
    
    
}
