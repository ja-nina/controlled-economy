/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlledeconomy.classes;

/**
 *
 * @author Windows 10
 */
public class MessengerValue {
    int value = 50;
    
   public MessengerValue(int n)
   {
      this.value = n;
   }
   
   //semaphore B hare
   public synchronized int getValue()
   {
      return value;
   }
   
   //semaphore B hare
   public synchronized void setValue(int n)
   {
      this.value = n;
   }
}
