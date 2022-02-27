/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlledeconomy.classes;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Windows 10
 */

public class Stock extends Asset{
    private float trasactionMargin;
    protected int maxNumberOfUnits;
    //price calculated by AMOUNT OF SHARES + company profit
    //

    @Override
    public float getMargin() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void drawPlot() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double computeMetric() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return 0;
    }
    
    

    
    
}
