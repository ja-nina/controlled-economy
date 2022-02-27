/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlledeconomy.classes;

import static java.lang.Integer.max;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Windows 10
 */
public class World implements Runnable{

    Asset asset;
    static Semaphore mutex = new Semaphore(1);
    public void run() {
        while(true){
           //asset = User.getInstance().getRandomAsset();
//            try {
//                System.out.println("changing influenceAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
//                //mutex.acquire();
//                //asset.influence = Math.random()*0.3 - 0.15;
//                //mutex.release();
//            }catch (InterruptedException ex) {
//                System.out.println("ERROR FROM WORLD!!!!!!!!!!!!!");
//                ex.printStackTrace();
//            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}
