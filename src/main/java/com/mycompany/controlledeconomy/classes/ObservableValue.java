/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlledeconomy.classes;

/**
 *
 * @author Windows 10
 */

import java.util.Observable;
public class ObservableValue extends Observable
{
   private volatile int  value = 0;
   private int rule = 2;
   public ObservableValue(int n, int rule)
   {
      this.value = n;
      this.rule = rule;
   }
   public void increase()
   {
      this.value +=1;
      setChanged();
       System.out.println("changed number of assets");
      //observers only get notified if change is big enough
      if(this.value%rule == 0){notifyObservers();}
   }
   
   public void decrease()
   {
      this.value -=1;
      setChanged();
      if(this.value%rule == 0){notifyObservers();}
   }
   
   //need a semaphore A  here 
   public int getValue()
   {
      return value;
   }
   
   //need a semaphore A here for sure
   public int setValue()
   {
      return value;
   }
}