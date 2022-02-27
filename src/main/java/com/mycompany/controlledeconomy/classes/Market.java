/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlledeconomy.classes;

/**
 *
 * @author Windows 10
 */

public interface Market {
    public void setTransactionsPerMinute(int tpm);
    public void setBearBull();

    public void notifyObservers();
    public void registerObservers();
    public void removeObservers();
    public String getName();
    public void setName(String name);
    public String getID();
    
    public int getCurrentIndex();
    public void addAsset(Asset asset);
    public void addToIndex();

}
