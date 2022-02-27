/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlledeconomy.classes;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Windows 10
 */

public class FactoryTraider implements Observer{
    
    
    public static float desiredRatio = (float) 0.5;
    private static float investorFundRatio = (float) 0.1;
    public int numberOfInvestors = 0;
    public int numberOfFunds = 0;
    private ObservableValue numberOfAssets = null;
    private MessengerValue BBratio = null;
    private User user;
    private ArrayList<Thread> threads;
    
    
    public FactoryTraider(User user, ObservableValue numberOfAssets, MessengerValue BBratio){
        this.user = user;
        this.numberOfAssets = numberOfAssets;
        this.BBratio = BBratio;
        this.threads = new ArrayList<Thread>();
    }
    
    
    // the number of traidors should be 1/10 of available assets
    @Override
    public void update(Observable o, Object arg) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("NUMBER OF ASSETS NOW " + numberOfAssets.getValue());
        while(numberOfFunds + numberOfInvestors/ ((float)numberOfAssets.getValue() + 0.01) < desiredRatio){
            //if(Math.random() > investorFundRatio || FactoryAsset.getNumberOfStocks() < 13){
            if(Math.random() > 0.5 || FactoryAsset.getNumberOfStocks() < 7){
                //create investor
                //System.out.println("CREATED INVESTOR");
                String ID = "INV" + "000000".substring((int) Math.ceil(numberOfInvestors/10)) +  Integer.toString(numberOfInvestors);
                Investor investor = new Investor(NameGenerator.getInvestorName(),NameGenerator.getInvestorSurname(), ID, 1000000* Math.random(), new Strategy(BBratio));
                Thread thread = new Thread(investor);
                thread.start();
                this.threads.add(thread);
                user.listOfTraders.add(investor);
                numberOfInvestors +=1;
            }else{
                //create fund
                System.out.println("CREATED INVESTMENT FUND");
                String ID = "FUN" + "000000".substring((int) Math.ceil(numberOfFunds/10)) +  Integer.toString(numberOfFunds);
                InvestmentFund invFund = new InvestmentFund(NameGenerator.getFundName(), "KW", ID, (double)1000000* Math.random(), new Strategy(BBratio));
                System.out.println("CREATED SSFULLY!");
                //try {
                    //try {
                        //System.out.println("created FUND: " + invFund.createFund().getName());
                        //invFund.createFund();
                        
                    //User.getInstance().addFund(invFund.createFund());
                //} catch (InterruptedException ex) {
                   // ex.printStackTrace();
                //}
                //} catch (InterruptedException ex) {
                //    ex.printStackTrace();
               //}
                Thread thread = new Thread(invFund);
                thread.start();
                this.threads.add(thread);
                user.listOfTraders.add(invFund);
                numberOfInvestors +=1;
            }
            
        }
        
        User.getInstance().setNumberOfTraders(numberOfInvestors + numberOfFunds);
    
    }
    

}