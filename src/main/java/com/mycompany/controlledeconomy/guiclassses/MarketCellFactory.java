/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlledeconomy.guiclassses;

/**
 *
 * @author Windows 10
 */

import com.mycompany.controlledeconomy.classes.CommodityMarket;
import com.mycompany.controlledeconomy.classes.Market;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
 
public class MarketCellFactory implements Callback<ListView<Market>, ListCell<Market>>
{
    @Override
    public ListCell<Market> call(ListView<Market> listview) 
    {
        return new MarketCell();
    }
}