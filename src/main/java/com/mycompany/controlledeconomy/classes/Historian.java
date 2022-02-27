/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlledeconomy.classes;

import static java.lang.Integer.max;

/**
 *
 * @author Windows 10
 */
public class Historian implements Runnable{
    private volatile long start;
    private volatile long end;
    private static Historian instance;
    public Asset currentAsset = null;
    
    static{
        try{
            instance = new Historian();
        }catch(Exception e){
            throw new RuntimeException("Exception occured in creating singleton instance");
        }
    }
    
    public static Historian summonHistorian(){
        return instance;
    }
    
    @Override
    public void run() {
        while(true){
            start = System.currentTimeMillis();
            int i = 0;
            //System.out.println("historian is working:     number of assets" + Integer.toString(User.getInstance().numberOfAssets.getValue()));
            //System.out.println("number of assets: " + User.getInstance().listOfAssets.size());
            while(i < User.getInstance().listOfAssets.size()){
                try{
                     User.getInstance().listOfAssets.get(i).computeHistory();
                   }catch (InterruptedException ex) {
                    ex.printStackTrace();}
                    i++;
            }
            end = System.currentTimeMillis();
            try {
                //start = System.currentTimeMillis();
                //System.out.println("sleeping ");
                Thread.sleep(300);
                //System.out.println("stopped sleeping at " + System.currentTimeMillis() );
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }  
        }
    
    
    public void setCurrentAsset(Asset asset){
        this.currentAsset = asset;
    }

    public Asset getCurrentAsset(){
        return this.currentAsset;
    }

    
}
